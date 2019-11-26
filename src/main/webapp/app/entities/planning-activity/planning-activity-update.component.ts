import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';
import { IPlanningActivity, PlanningActivity } from 'app/shared/model/planning-activity.model';
import { PlanningActivityService } from './planning-activity.service';
import { ITrimesterPlanning } from 'app/shared/model/trimester-planning.model';
import { TrimesterPlanningService } from 'app/entities/trimester-planning/trimester-planning.service';
import { IProyectActivity } from 'app/shared/model/proyect-activity.model';
import { ProyectActivityService } from 'app/entities/proyect-activity/proyect-activity.service';

@Component({
  selector: 'jhi-planning-activity-update',
  templateUrl: './planning-activity-update.component.html'
})
export class PlanningActivityUpdateComponent implements OnInit {
  isSaving: boolean;

  trimesterplannings: ITrimesterPlanning[];

  proyectactivities: IProyectActivity[];

  editForm = this.fb.group({
    id: [],
    trimesterPlanningId: [null, Validators.required],
    proyectActivityId: [null, Validators.required]
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected planningActivityService: PlanningActivityService,
    protected trimesterPlanningService: TrimesterPlanningService,
    protected proyectActivityService: ProyectActivityService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ planningActivity }) => {
      this.updateForm(planningActivity);
    });
    this.trimesterPlanningService
      .query()
      .subscribe(
        (res: HttpResponse<ITrimesterPlanning[]>) => (this.trimesterplannings = res.body),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
    this.proyectActivityService
      .query()
      .subscribe(
        (res: HttpResponse<IProyectActivity[]>) => (this.proyectactivities = res.body),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  updateForm(planningActivity: IPlanningActivity) {
    this.editForm.patchValue({
      id: planningActivity.id,
      trimesterPlanningId: planningActivity.trimesterPlanningId,
      proyectActivityId: planningActivity.proyectActivityId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const planningActivity = this.createFromForm();
    if (planningActivity.id !== undefined) {
      this.subscribeToSaveResponse(this.planningActivityService.update(planningActivity));
    } else {
      this.subscribeToSaveResponse(this.planningActivityService.create(planningActivity));
    }
  }

  private createFromForm(): IPlanningActivity {
    return {
      ...new PlanningActivity(),
      id: this.editForm.get(['id']).value,
      trimesterPlanningId: this.editForm.get(['trimesterPlanningId']).value,
      proyectActivityId: this.editForm.get(['proyectActivityId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPlanningActivity>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  trackTrimesterPlanningById(index: number, item: ITrimesterPlanning) {
    return item.id;
  }

  trackProyectActivityById(index: number, item: IProyectActivity) {
    return item.id;
  }
}
