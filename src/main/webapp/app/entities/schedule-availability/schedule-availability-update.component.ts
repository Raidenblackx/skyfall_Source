import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';
import { IScheduleAvailability, ScheduleAvailability } from 'app/shared/model/schedule-availability.model';
import { ScheduleAvailabilityService } from './schedule-availability.service';
import { IInstructorLinking } from 'app/shared/model/instructor-linking.model';
import { InstructorLinkingService } from 'app/entities/instructor-linking/instructor-linking.service';
import { IJourneyInstructor } from 'app/shared/model/journey-instructor.model';
import { JourneyInstructorService } from 'app/entities/journey-instructor/journey-instructor.service';

@Component({
  selector: 'jhi-schedule-availability-update',
  templateUrl: './schedule-availability-update.component.html'
})
export class ScheduleAvailabilityUpdateComponent implements OnInit {
  isSaving: boolean;

  instructorlinkings: IInstructorLinking[];

  journeyinstructors: IJourneyInstructor[];

  editForm = this.fb.group({
    id: [],
    instructorLinkingId: [null, Validators.required],
    journeyInstructorId: [null, Validators.required]
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected scheduleAvailabilityService: ScheduleAvailabilityService,
    protected instructorLinkingService: InstructorLinkingService,
    protected journeyInstructorService: JourneyInstructorService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ scheduleAvailability }) => {
      this.updateForm(scheduleAvailability);
    });
    this.instructorLinkingService
      .query()
      .subscribe(
        (res: HttpResponse<IInstructorLinking[]>) => (this.instructorlinkings = res.body),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
    this.journeyInstructorService
      .query()
      .subscribe(
        (res: HttpResponse<IJourneyInstructor[]>) => (this.journeyinstructors = res.body),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  updateForm(scheduleAvailability: IScheduleAvailability) {
    this.editForm.patchValue({
      id: scheduleAvailability.id,
      instructorLinkingId: scheduleAvailability.instructorLinkingId,
      journeyInstructorId: scheduleAvailability.journeyInstructorId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const scheduleAvailability = this.createFromForm();
    if (scheduleAvailability.id !== undefined) {
      this.subscribeToSaveResponse(this.scheduleAvailabilityService.update(scheduleAvailability));
    } else {
      this.subscribeToSaveResponse(this.scheduleAvailabilityService.create(scheduleAvailability));
    }
  }

  private createFromForm(): IScheduleAvailability {
    return {
      ...new ScheduleAvailability(),
      id: this.editForm.get(['id']).value,
      instructorLinkingId: this.editForm.get(['instructorLinkingId']).value,
      journeyInstructorId: this.editForm.get(['journeyInstructorId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IScheduleAvailability>>) {
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

  trackInstructorLinkingById(index: number, item: IInstructorLinking) {
    return item.id;
  }

  trackJourneyInstructorById(index: number, item: IJourneyInstructor) {
    return item.id;
  }
}
