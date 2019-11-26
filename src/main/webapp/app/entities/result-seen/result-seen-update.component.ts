import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';
import { IResultSeen, ResultSeen } from 'app/shared/model/result-seen.model';
import { ResultSeenService } from './result-seen.service';
import { ILearningResult } from 'app/shared/model/learning-result.model';
import { LearningResultService } from 'app/entities/learning-result/learning-result.service';
import { ICourseHasTrimester } from 'app/shared/model/course-has-trimester.model';
import { CourseHasTrimesterService } from 'app/entities/course-has-trimester/course-has-trimester.service';

@Component({
  selector: 'jhi-result-seen-update',
  templateUrl: './result-seen-update.component.html'
})
export class ResultSeenUpdateComponent implements OnInit {
  isSaving: boolean;

  learningresults: ILearningResult[];

  coursehastrimesters: ICourseHasTrimester[];

  editForm = this.fb.group({
    id: [],
    learningResultId: [null, Validators.required],
    courseHasTrimesterId: [null, Validators.required]
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected resultSeenService: ResultSeenService,
    protected learningResultService: LearningResultService,
    protected courseHasTrimesterService: CourseHasTrimesterService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ resultSeen }) => {
      this.updateForm(resultSeen);
    });
    this.learningResultService
      .query()
      .subscribe(
        (res: HttpResponse<ILearningResult[]>) => (this.learningresults = res.body),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
    this.courseHasTrimesterService
      .query()
      .subscribe(
        (res: HttpResponse<ICourseHasTrimester[]>) => (this.coursehastrimesters = res.body),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  updateForm(resultSeen: IResultSeen) {
    this.editForm.patchValue({
      id: resultSeen.id,
      learningResultId: resultSeen.learningResultId,
      courseHasTrimesterId: resultSeen.courseHasTrimesterId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const resultSeen = this.createFromForm();
    if (resultSeen.id !== undefined) {
      this.subscribeToSaveResponse(this.resultSeenService.update(resultSeen));
    } else {
      this.subscribeToSaveResponse(this.resultSeenService.create(resultSeen));
    }
  }

  private createFromForm(): IResultSeen {
    return {
      ...new ResultSeen(),
      id: this.editForm.get(['id']).value,
      learningResultId: this.editForm.get(['learningResultId']).value,
      courseHasTrimesterId: this.editForm.get(['courseHasTrimesterId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IResultSeen>>) {
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

  trackCourseHasTrimesterById(index: number, item: ICourseHasTrimester) {
    return item.id;
  }
}
