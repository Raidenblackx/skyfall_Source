import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';
import { ILearningResult, LearningResult } from 'app/shared/model/learning-result.model';
import { LearningResultService } from './learning-result.service';
import { ICompetition } from 'app/shared/model/competition.model';
import { CompetitionService } from 'app/entities/competition/competition.service';

@Component({
  selector: 'jhi-learning-result-update',
  templateUrl: './learning-result-update.component.html'
})
export class LearningResultUpdateComponent implements OnInit {
  isSaving: boolean;

  competitions: ICompetition[];

  editForm = this.fb.group({
    id: [],
    codeResult: [null, [Validators.required, Validators.maxLength(40)]],
    denomination: [null, [Validators.required, Validators.maxLength(1000)]],
    competitionId: [null, Validators.required]
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected learningResultService: LearningResultService,
    protected competitionService: CompetitionService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ learningResult }) => {
      this.updateForm(learningResult);
    });
    this.competitionService
      .query()
      .subscribe(
        (res: HttpResponse<ICompetition[]>) => (this.competitions = res.body),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  updateForm(learningResult: ILearningResult) {
    this.editForm.patchValue({
      id: learningResult.id,
      codeResult: learningResult.codeResult,
      denomination: learningResult.denomination,
      competitionId: learningResult.competitionId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const learningResult = this.createFromForm();
    if (learningResult.id !== undefined) {
      this.subscribeToSaveResponse(this.learningResultService.update(learningResult));
    } else {
      this.subscribeToSaveResponse(this.learningResultService.create(learningResult));
    }
  }

  private createFromForm(): ILearningResult {
    return {
      ...new LearningResult(),
      id: this.editForm.get(['id']).value,
      codeResult: this.editForm.get(['codeResult']).value,
      denomination: this.editForm.get(['denomination']).value,
      competitionId: this.editForm.get(['competitionId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ILearningResult>>) {
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

  trackCompetitionById(index: number, item: ICompetition) {
    return item.id;
  }
}
