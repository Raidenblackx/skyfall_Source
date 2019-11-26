import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';
import { ILimitationEnvironment, LimitationEnvironment } from 'app/shared/model/limitation-environment.model';
import { LimitationEnvironmentService } from './limitation-environment.service';
import { ILearningResult } from 'app/shared/model/learning-result.model';
import { LearningResultService } from 'app/entities/learning-result/learning-result.service';
import { IAmbient } from 'app/shared/model/ambient.model';
import { AmbientService } from 'app/entities/ambient/ambient.service';

@Component({
  selector: 'jhi-limitation-environment-update',
  templateUrl: './limitation-environment-update.component.html'
})
export class LimitationEnvironmentUpdateComponent implements OnInit {
  isSaving: boolean;

  learningresults: ILearningResult[];

  ambients: IAmbient[];

  editForm = this.fb.group({
    id: [],
    learningResultId: [null, Validators.required],
    ambientId: [null, Validators.required]
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected limitationEnvironmentService: LimitationEnvironmentService,
    protected learningResultService: LearningResultService,
    protected ambientService: AmbientService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ limitationEnvironment }) => {
      this.updateForm(limitationEnvironment);
    });
    this.learningResultService
      .query()
      .subscribe(
        (res: HttpResponse<ILearningResult[]>) => (this.learningresults = res.body),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
    this.ambientService
      .query()
      .subscribe((res: HttpResponse<IAmbient[]>) => (this.ambients = res.body), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(limitationEnvironment: ILimitationEnvironment) {
    this.editForm.patchValue({
      id: limitationEnvironment.id,
      learningResultId: limitationEnvironment.learningResultId,
      ambientId: limitationEnvironment.ambientId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const limitationEnvironment = this.createFromForm();
    if (limitationEnvironment.id !== undefined) {
      this.subscribeToSaveResponse(this.limitationEnvironmentService.update(limitationEnvironment));
    } else {
      this.subscribeToSaveResponse(this.limitationEnvironmentService.create(limitationEnvironment));
    }
  }

  private createFromForm(): ILimitationEnvironment {
    return {
      ...new LimitationEnvironment(),
      id: this.editForm.get(['id']).value,
      learningResultId: this.editForm.get(['learningResultId']).value,
      ambientId: this.editForm.get(['ambientId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ILimitationEnvironment>>) {
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

  trackAmbientById(index: number, item: IAmbient) {
    return item.id;
  }
}
