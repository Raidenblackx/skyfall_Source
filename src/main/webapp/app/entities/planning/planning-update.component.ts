import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { IPlanning, Planning } from 'app/shared/model/planning.model';
import { PlanningService } from './planning.service';

@Component({
  selector: 'jhi-planning-update',
  templateUrl: './planning-update.component.html'
})
export class PlanningUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    code: [null, [Validators.required, Validators.maxLength(40)]],
    date: [null, [Validators.required]],
    statePlannig: [null, [Validators.required]]
  });

  constructor(protected planningService: PlanningService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ planning }) => {
      this.updateForm(planning);
    });
  }

  updateForm(planning: IPlanning) {
    this.editForm.patchValue({
      id: planning.id,
      code: planning.code,
      date: planning.date != null ? planning.date.format(DATE_TIME_FORMAT) : null,
      statePlannig: planning.statePlannig
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const planning = this.createFromForm();
    if (planning.id !== undefined) {
      this.subscribeToSaveResponse(this.planningService.update(planning));
    } else {
      this.subscribeToSaveResponse(this.planningService.create(planning));
    }
  }

  private createFromForm(): IPlanning {
    return {
      ...new Planning(),
      id: this.editForm.get(['id']).value,
      code: this.editForm.get(['code']).value,
      date: this.editForm.get(['date']).value != null ? moment(this.editForm.get(['date']).value, DATE_TIME_FORMAT) : undefined,
      statePlannig: this.editForm.get(['statePlannig']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPlanning>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
