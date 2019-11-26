import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';
import { ITrimesterPlanning, TrimesterPlanning } from 'app/shared/model/trimester-planning.model';
import { TrimesterPlanningService } from './trimester-planning.service';
import { ILearningResult } from 'app/shared/model/learning-result.model';
import { LearningResultService } from 'app/entities/learning-result/learning-result.service';
import { ITrimester } from 'app/shared/model/trimester.model';
import { TrimesterService } from 'app/entities/trimester/trimester.service';
import { IPlanning } from 'app/shared/model/planning.model';
import { PlanningService } from 'app/entities/planning/planning.service';

@Component({
  selector: 'jhi-trimester-planning-update',
  templateUrl: './trimester-planning-update.component.html'
})
export class TrimesterPlanningUpdateComponent implements OnInit {
  isSaving: boolean;

  learningresults: ILearningResult[];

  trimesters: ITrimester[];

  plannings: IPlanning[];

  editForm = this.fb.group({
    id: [],
    learningResultId: [null, Validators.required],
    trimesterId: [null, Validators.required],
    planningId: [null, Validators.required]
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected trimesterPlanningService: TrimesterPlanningService,
    protected learningResultService: LearningResultService,
    protected trimesterService: TrimesterService,
    protected planningService: PlanningService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ trimesterPlanning }) => {
      this.updateForm(trimesterPlanning);
    });
    this.learningResultService
      .query()
      .subscribe(
        (res: HttpResponse<ILearningResult[]>) => (this.learningresults = res.body),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
    this.trimesterService
      .query()
      .subscribe((res: HttpResponse<ITrimester[]>) => (this.trimesters = res.body), (res: HttpErrorResponse) => this.onError(res.message));
    this.planningService
      .query()
      .subscribe((res: HttpResponse<IPlanning[]>) => (this.plannings = res.body), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(trimesterPlanning: ITrimesterPlanning) {
    this.editForm.patchValue({
      id: trimesterPlanning.id,
      learningResultId: trimesterPlanning.learningResultId,
      trimesterId: trimesterPlanning.trimesterId,
      planningId: trimesterPlanning.planningId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const trimesterPlanning = this.createFromForm();
    if (trimesterPlanning.id !== undefined) {
      this.subscribeToSaveResponse(this.trimesterPlanningService.update(trimesterPlanning));
    } else {
      this.subscribeToSaveResponse(this.trimesterPlanningService.create(trimesterPlanning));
    }
  }

  private createFromForm(): ITrimesterPlanning {
    return {
      ...new TrimesterPlanning(),
      id: this.editForm.get(['id']).value,
      learningResultId: this.editForm.get(['learningResultId']).value,
      trimesterId: this.editForm.get(['trimesterId']).value,
      planningId: this.editForm.get(['planningId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITrimesterPlanning>>) {
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

  trackLearningResultById(index: number, item: ILearningResult) {
    return item.id;
  }

  trackTrimesterById(index: number, item: ITrimester) {
    return item.id;
  }

  trackPlanningById(index: number, item: IPlanning) {
    return item.id;
  }
}
