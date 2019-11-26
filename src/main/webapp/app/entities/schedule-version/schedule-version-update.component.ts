import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';
import { IScheduleVersion, ScheduleVersion } from 'app/shared/model/schedule-version.model';
import { ScheduleVersionService } from './schedule-version.service';
import { ICurrentQuarter } from 'app/shared/model/current-quarter.model';
import { CurrentQuarterService } from 'app/entities/current-quarter/current-quarter.service';

@Component({
  selector: 'jhi-schedule-version-update',
  templateUrl: './schedule-version-update.component.html'
})
export class ScheduleVersionUpdateComponent implements OnInit {
  isSaving: boolean;

  currentquarters: ICurrentQuarter[];

  editForm = this.fb.group({
    id: [],
    numberVersion: [null, [Validators.required, Validators.maxLength(40)]],
    stateScheduleVersion: [null, [Validators.required]],
    currentQuarterId: [null, Validators.required]
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected scheduleVersionService: ScheduleVersionService,
    protected currentQuarterService: CurrentQuarterService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ scheduleVersion }) => {
      this.updateForm(scheduleVersion);
    });
    this.currentQuarterService
      .query()
      .subscribe(
        (res: HttpResponse<ICurrentQuarter[]>) => (this.currentquarters = res.body),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  updateForm(scheduleVersion: IScheduleVersion) {
    this.editForm.patchValue({
      id: scheduleVersion.id,
      numberVersion: scheduleVersion.numberVersion,
      stateScheduleVersion: scheduleVersion.stateScheduleVersion,
      currentQuarterId: scheduleVersion.currentQuarterId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const scheduleVersion = this.createFromForm();
    if (scheduleVersion.id !== undefined) {
      this.subscribeToSaveResponse(this.scheduleVersionService.update(scheduleVersion));
    } else {
      this.subscribeToSaveResponse(this.scheduleVersionService.create(scheduleVersion));
    }
  }

  private createFromForm(): IScheduleVersion {
    return {
      ...new ScheduleVersion(),
      id: this.editForm.get(['id']).value,
      numberVersion: this.editForm.get(['numberVersion']).value,
      stateScheduleVersion: this.editForm.get(['stateScheduleVersion']).value,
      currentQuarterId: this.editForm.get(['currentQuarterId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IScheduleVersion>>) {
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

  trackCurrentQuarterById(index: number, item: ICurrentQuarter) {
    return item.id;
  }
}
