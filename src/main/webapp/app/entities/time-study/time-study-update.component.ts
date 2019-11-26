import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';
import { ITimeStudy, TimeStudy } from 'app/shared/model/time-study.model';
import { TimeStudyService } from './time-study.service';
import { IJourneyInstructor } from 'app/shared/model/journey-instructor.model';
import { JourneyInstructorService } from 'app/entities/journey-instructor/journey-instructor.service';
import { IDay } from 'app/shared/model/day.model';
import { DayService } from 'app/entities/day/day.service';

@Component({
  selector: 'jhi-time-study-update',
  templateUrl: './time-study-update.component.html'
})
export class TimeStudyUpdateComponent implements OnInit {
  isSaving: boolean;

  journeyinstructors: IJourneyInstructor[];

  days: IDay[];

  editForm = this.fb.group({
    id: [],
    startTime: [null, [Validators.required]],
    endTime: [null, [Validators.required]],
    journeyInstructorId: [null, Validators.required],
    dayId: [null, Validators.required]
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected timeStudyService: TimeStudyService,
    protected journeyInstructorService: JourneyInstructorService,
    protected dayService: DayService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ timeStudy }) => {
      this.updateForm(timeStudy);
    });
    this.journeyInstructorService
      .query()
      .subscribe(
        (res: HttpResponse<IJourneyInstructor[]>) => (this.journeyinstructors = res.body),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
    this.dayService
      .query()
      .subscribe((res: HttpResponse<IDay[]>) => (this.days = res.body), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(timeStudy: ITimeStudy) {
    this.editForm.patchValue({
      id: timeStudy.id,
      startTime: timeStudy.startTime,
      endTime: timeStudy.endTime,
      journeyInstructorId: timeStudy.journeyInstructorId,
      dayId: timeStudy.dayId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const timeStudy = this.createFromForm();
    if (timeStudy.id !== undefined) {
      this.subscribeToSaveResponse(this.timeStudyService.update(timeStudy));
    } else {
      this.subscribeToSaveResponse(this.timeStudyService.create(timeStudy));
    }
  }

  private createFromForm(): ITimeStudy {
    return {
      ...new TimeStudy(),
      id: this.editForm.get(['id']).value,
      startTime: this.editForm.get(['startTime']).value,
      endTime: this.editForm.get(['endTime']).value,
      journeyInstructorId: this.editForm.get(['journeyInstructorId']).value,
      dayId: this.editForm.get(['dayId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITimeStudy>>) {
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

  trackJourneyInstructorById(index: number, item: IJourneyInstructor) {
    return item.id;
  }

  trackDayById(index: number, item: IDay) {
    return item.id;
  }
}
