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
import { ISchedule, Schedule } from 'app/shared/model/schedule.model';
import { ScheduleService } from './schedule.service';
import { ICourseHasTrimester } from 'app/shared/model/course-has-trimester.model';
import { CourseHasTrimesterService } from 'app/entities/course-has-trimester/course-has-trimester.service';
import { IAmbient } from 'app/shared/model/ambient.model';
import { AmbientService } from 'app/entities/ambient/ambient.service';
import { IInstructor } from 'app/shared/model/instructor.model';
import { InstructorService } from 'app/entities/instructor/instructor.service';
import { IDay } from 'app/shared/model/day.model';
import { DayService } from 'app/entities/day/day.service';
import { IScheduleVersion } from 'app/shared/model/schedule-version.model';
import { ScheduleVersionService } from 'app/entities/schedule-version/schedule-version.service';
import { IModality } from 'app/shared/model/modality.model';
import { ModalityService } from 'app/entities/modality/modality.service';

@Component({
  selector: 'jhi-schedule-update',
  templateUrl: './schedule-update.component.html'
})
export class ScheduleUpdateComponent implements OnInit {
  isSaving: boolean;

  coursehastrimesters: ICourseHasTrimester[];

  ambients: IAmbient[];

  instructors: IInstructor[];

  days: IDay[];

  scheduleversions: IScheduleVersion[];

  modalities: IModality[];

  editForm = this.fb.group({
    id: [],
    startTime: [null, [Validators.required]],
    endTime: [null, [Validators.required]],
    courseHasTrimesterId: [null, Validators.required],
    ambientId: [null, Validators.required],
    instructorId: [null, Validators.required],
    dayId: [null, Validators.required],
    scheduleVersionId: [null, Validators.required],
    modalityId: [null, Validators.required]
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected scheduleService: ScheduleService,
    protected courseHasTrimesterService: CourseHasTrimesterService,
    protected ambientService: AmbientService,
    protected instructorService: InstructorService,
    protected dayService: DayService,
    protected scheduleVersionService: ScheduleVersionService,
    protected modalityService: ModalityService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ schedule }) => {
      this.updateForm(schedule);
    });
    this.courseHasTrimesterService
      .query()
      .subscribe(
        (res: HttpResponse<ICourseHasTrimester[]>) => (this.coursehastrimesters = res.body),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
    this.ambientService
      .query()
      .subscribe((res: HttpResponse<IAmbient[]>) => (this.ambients = res.body), (res: HttpErrorResponse) => this.onError(res.message));
    this.instructorService
      .query()
      .subscribe(
        (res: HttpResponse<IInstructor[]>) => (this.instructors = res.body),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
    this.dayService
      .query()
      .subscribe((res: HttpResponse<IDay[]>) => (this.days = res.body), (res: HttpErrorResponse) => this.onError(res.message));
    this.scheduleVersionService
      .query()
      .subscribe(
        (res: HttpResponse<IScheduleVersion[]>) => (this.scheduleversions = res.body),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
    this.modalityService
      .query()
      .subscribe((res: HttpResponse<IModality[]>) => (this.modalities = res.body), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(schedule: ISchedule) {
    this.editForm.patchValue({
      id: schedule.id,
      startTime: schedule.startTime != null ? schedule.startTime.format(DATE_TIME_FORMAT) : null,
      endTime: schedule.endTime != null ? schedule.endTime.format(DATE_TIME_FORMAT) : null,
      courseHasTrimesterId: schedule.courseHasTrimesterId,
      ambientId: schedule.ambientId,
      instructorId: schedule.instructorId,
      dayId: schedule.dayId,
      scheduleVersionId: schedule.scheduleVersionId,
      modalityId: schedule.modalityId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const schedule = this.createFromForm();
    if (schedule.id !== undefined) {
      this.subscribeToSaveResponse(this.scheduleService.update(schedule));
    } else {
      this.subscribeToSaveResponse(this.scheduleService.create(schedule));
    }
  }

  private createFromForm(): ISchedule {
    return {
      ...new Schedule(),
      id: this.editForm.get(['id']).value,
      startTime:
        this.editForm.get(['startTime']).value != null ? moment(this.editForm.get(['startTime']).value, DATE_TIME_FORMAT) : undefined,
      endTime: this.editForm.get(['endTime']).value != null ? moment(this.editForm.get(['endTime']).value, DATE_TIME_FORMAT) : undefined,
      courseHasTrimesterId: this.editForm.get(['courseHasTrimesterId']).value,
      ambientId: this.editForm.get(['ambientId']).value,
      instructorId: this.editForm.get(['instructorId']).value,
      dayId: this.editForm.get(['dayId']).value,
      scheduleVersionId: this.editForm.get(['scheduleVersionId']).value,
      modalityId: this.editForm.get(['modalityId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISchedule>>) {
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

  trackCourseHasTrimesterById(index: number, item: ICourseHasTrimester) {
    return item.id;
  }

  trackAmbientById(index: number, item: IAmbient) {
    return item.id;
  }

  trackInstructorById(index: number, item: IInstructor) {
    return item.id;
  }

  trackDayById(index: number, item: IDay) {
    return item.id;
  }

  trackScheduleVersionById(index: number, item: IScheduleVersion) {
    return item.id;
  }

  trackModalityById(index: number, item: IModality) {
    return item.id;
  }
}
