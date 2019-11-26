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
import { ICurrentQuarter, CurrentQuarter } from 'app/shared/model/current-quarter.model';
import { CurrentQuarterService } from './current-quarter.service';
import { IYear } from 'app/shared/model/year.model';
import { YearService } from 'app/entities/year/year.service';

@Component({
  selector: 'jhi-current-quarter-update',
  templateUrl: './current-quarter-update.component.html'
})
export class CurrentQuarterUpdateComponent implements OnInit {
  isSaving: boolean;

  years: IYear[];

  editForm = this.fb.group({
    id: [],
    scheduledQuarter: [null, [Validators.required]],
    startDate: [null, [Validators.required]],
    endDate: [null, [Validators.required]],
    stateCurrentQuarter: [null, [Validators.required]],
    yearId: [null, Validators.required]
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected currentQuarterService: CurrentQuarterService,
    protected yearService: YearService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ currentQuarter }) => {
      this.updateForm(currentQuarter);
    });
    this.yearService
      .query()
      .subscribe((res: HttpResponse<IYear[]>) => (this.years = res.body), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(currentQuarter: ICurrentQuarter) {
    this.editForm.patchValue({
      id: currentQuarter.id,
      scheduledQuarter: currentQuarter.scheduledQuarter,
      startDate: currentQuarter.startDate != null ? currentQuarter.startDate.format(DATE_TIME_FORMAT) : null,
      endDate: currentQuarter.endDate != null ? currentQuarter.endDate.format(DATE_TIME_FORMAT) : null,
      stateCurrentQuarter: currentQuarter.stateCurrentQuarter,
      yearId: currentQuarter.yearId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const currentQuarter = this.createFromForm();
    if (currentQuarter.id !== undefined) {
      this.subscribeToSaveResponse(this.currentQuarterService.update(currentQuarter));
    } else {
      this.subscribeToSaveResponse(this.currentQuarterService.create(currentQuarter));
    }
  }

  private createFromForm(): ICurrentQuarter {
    return {
      ...new CurrentQuarter(),
      id: this.editForm.get(['id']).value,
      scheduledQuarter: this.editForm.get(['scheduledQuarter']).value,
      startDate:
        this.editForm.get(['startDate']).value != null ? moment(this.editForm.get(['startDate']).value, DATE_TIME_FORMAT) : undefined,
      endDate: this.editForm.get(['endDate']).value != null ? moment(this.editForm.get(['endDate']).value, DATE_TIME_FORMAT) : undefined,
      stateCurrentQuarter: this.editForm.get(['stateCurrentQuarter']).value,
      yearId: this.editForm.get(['yearId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICurrentQuarter>>) {
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

  trackYearById(index: number, item: IYear) {
    return item.id;
  }
}
