import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';
import { ICourse, Course } from 'app/shared/model/course.model';
import { CourseService } from './course.service';
import { ICourseState } from 'app/shared/model/course-state.model';
import { CourseStateService } from 'app/entities/course-state/course-state.service';
import { IWorkingDay } from 'app/shared/model/working-day.model';
import { WorkingDayService } from 'app/entities/working-day/working-day.service';
import { IProgram } from 'app/shared/model/program.model';
import { ProgramService } from 'app/entities/program/program.service';

@Component({
  selector: 'jhi-course-update',
  templateUrl: './course-update.component.html'
})
export class CourseUpdateComponent implements OnInit {
  isSaving: boolean;

  coursestates: ICourseState[];

  workingdays: IWorkingDay[];

  programs: IProgram[];

  editForm = this.fb.group({
    id: [],
    courseNumber: [null, [Validators.required, Validators.maxLength(40)]],
    startDate: [null, [Validators.required]],
    endDate: [null, [Validators.required]],
    route: [null, [Validators.required, Validators.maxLength(40)]],
    courseStateId: [null, Validators.required],
    workingDayId: [],
    programId: [null, Validators.required]
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected courseService: CourseService,
    protected courseStateService: CourseStateService,
    protected workingDayService: WorkingDayService,
    protected programService: ProgramService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ course }) => {
      this.updateForm(course);
    });
    this.courseStateService
      .query()
      .subscribe(
        (res: HttpResponse<ICourseState[]>) => (this.coursestates = res.body),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
    this.workingDayService
      .query()
      .subscribe(
        (res: HttpResponse<IWorkingDay[]>) => (this.workingdays = res.body),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
    this.programService
      .query()
      .subscribe((res: HttpResponse<IProgram[]>) => (this.programs = res.body), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(course: ICourse) {
    this.editForm.patchValue({
      id: course.id,
      courseNumber: course.courseNumber,
      startDate: course.startDate != null ? course.startDate.format(DATE_TIME_FORMAT) : null,
      endDate: course.endDate != null ? course.endDate.format(DATE_TIME_FORMAT) : null,
      route: course.route,
      courseStateId: course.courseStateId,
      workingDayId: course.workingDayId,
      programId: course.programId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const course = this.createFromForm();
    if (course.id !== undefined) {
      this.subscribeToSaveResponse(this.courseService.update(course));
    } else {
      this.subscribeToSaveResponse(this.courseService.create(course));
    }
  }

  private createFromForm(): ICourse {
    return {
      ...new Course(),
      id: this.editForm.get(['id']).value,
      courseNumber: this.editForm.get(['courseNumber']).value,
      startDate:
        this.editForm.get(['startDate']).value != null ? moment(this.editForm.get(['startDate']).value, DATE_TIME_FORMAT) : undefined,
      endDate: this.editForm.get(['endDate']).value != null ? moment(this.editForm.get(['endDate']).value, DATE_TIME_FORMAT) : undefined,
      route: this.editForm.get(['route']).value,
      courseStateId: this.editForm.get(['courseStateId']).value,
      workingDayId: this.editForm.get(['workingDayId']).value,
      programId: this.editForm.get(['programId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICourse>>) {
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

  trackCourseStateById(index: number, item: ICourseState) {
    return item.id;
  }

  trackWorkingDayById(index: number, item: IWorkingDay) {
    return item.id;
  }

  trackProgramById(index: number, item: IProgram) {
    return item.id;
  }
}
