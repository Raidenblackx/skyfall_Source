import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';
import { ICourseHasTrimester, CourseHasTrimester } from 'app/shared/model/course-has-trimester.model';
import { CourseHasTrimesterService } from './course-has-trimester.service';
import { ICourse } from 'app/shared/model/course.model';
import { CourseService } from 'app/entities/course/course.service';
import { ITrimester } from 'app/shared/model/trimester.model';
import { TrimesterService } from 'app/entities/trimester/trimester.service';

@Component({
  selector: 'jhi-course-has-trimester-update',
  templateUrl: './course-has-trimester-update.component.html'
})
export class CourseHasTrimesterUpdateComponent implements OnInit {
  isSaving: boolean;

  courses: ICourse[];

  trimesters: ITrimester[];

  editForm = this.fb.group({
    id: [],
    courseId: [null, Validators.required],
    trimesterId: [null, Validators.required]
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected courseHasTrimesterService: CourseHasTrimesterService,
    protected courseService: CourseService,
    protected trimesterService: TrimesterService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ courseHasTrimester }) => {
      this.updateForm(courseHasTrimester);
    });
    this.courseService
      .query()
      .subscribe((res: HttpResponse<ICourse[]>) => (this.courses = res.body), (res: HttpErrorResponse) => this.onError(res.message));
    this.trimesterService
      .query()
      .subscribe((res: HttpResponse<ITrimester[]>) => (this.trimesters = res.body), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(courseHasTrimester: ICourseHasTrimester) {
    this.editForm.patchValue({
      id: courseHasTrimester.id,
      courseId: courseHasTrimester.courseId,
      trimesterId: courseHasTrimester.trimesterId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const courseHasTrimester = this.createFromForm();
    if (courseHasTrimester.id !== undefined) {
      this.subscribeToSaveResponse(this.courseHasTrimesterService.update(courseHasTrimester));
    } else {
      this.subscribeToSaveResponse(this.courseHasTrimesterService.create(courseHasTrimester));
    }
  }

  private createFromForm(): ICourseHasTrimester {
    return {
      ...new CourseHasTrimester(),
      id: this.editForm.get(['id']).value,
      courseId: this.editForm.get(['courseId']).value,
      trimesterId: this.editForm.get(['trimesterId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICourseHasTrimester>>) {
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

  trackCourseById(index: number, item: ICourse) {
    return item.id;
  }

  trackTrimesterById(index: number, item: ITrimester) {
    return item.id;
  }
}
