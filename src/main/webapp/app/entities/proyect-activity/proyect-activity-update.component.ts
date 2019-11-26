import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';
import { IProyectActivity, ProyectActivity } from 'app/shared/model/proyect-activity.model';
import { ProyectActivityService } from './proyect-activity.service';
import { IPhase } from 'app/shared/model/phase.model';
import { PhaseService } from 'app/entities/phase/phase.service';

@Component({
  selector: 'jhi-proyect-activity-update',
  templateUrl: './proyect-activity-update.component.html'
})
export class ProyectActivityUpdateComponent implements OnInit {
  isSaving: boolean;

  phases: IPhase[];

  editForm = this.fb.group({
    id: [],
    numberProyectActivity: [null, [Validators.required]],
    descriptionActivity: [null, [Validators.required, Validators.maxLength(400)]],
    stateProyectActivity: [null, [Validators.required]],
    phaseId: [null, Validators.required]
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected proyectActivityService: ProyectActivityService,
    protected phaseService: PhaseService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ proyectActivity }) => {
      this.updateForm(proyectActivity);
    });
    this.phaseService
      .query()
      .subscribe((res: HttpResponse<IPhase[]>) => (this.phases = res.body), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(proyectActivity: IProyectActivity) {
    this.editForm.patchValue({
      id: proyectActivity.id,
      numberProyectActivity: proyectActivity.numberProyectActivity,
      descriptionActivity: proyectActivity.descriptionActivity,
      stateProyectActivity: proyectActivity.stateProyectActivity,
      phaseId: proyectActivity.phaseId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const proyectActivity = this.createFromForm();
    if (proyectActivity.id !== undefined) {
      this.subscribeToSaveResponse(this.proyectActivityService.update(proyectActivity));
    } else {
      this.subscribeToSaveResponse(this.proyectActivityService.create(proyectActivity));
    }
  }

  private createFromForm(): IProyectActivity {
    return {
      ...new ProyectActivity(),
      id: this.editForm.get(['id']).value,
      numberProyectActivity: this.editForm.get(['numberProyectActivity']).value,
      descriptionActivity: this.editForm.get(['descriptionActivity']).value,
      stateProyectActivity: this.editForm.get(['stateProyectActivity']).value,
      phaseId: this.editForm.get(['phaseId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProyectActivity>>) {
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

  trackPhaseById(index: number, item: IPhase) {
    return item.id;
  }
}
