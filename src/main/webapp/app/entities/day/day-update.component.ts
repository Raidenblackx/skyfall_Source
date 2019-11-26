import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IDay, Day } from 'app/shared/model/day.model';
import { DayService } from './day.service';

@Component({
  selector: 'jhi-day-update',
  templateUrl: './day-update.component.html'
})
export class DayUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    nameDay: [null, [Validators.required, Validators.maxLength(40)]],
    stateDay: [null, [Validators.required]]
  });

  constructor(protected dayService: DayService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ day }) => {
      this.updateForm(day);
    });
  }

  updateForm(day: IDay) {
    this.editForm.patchValue({
      id: day.id,
      nameDay: day.nameDay,
      stateDay: day.stateDay
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const day = this.createFromForm();
    if (day.id !== undefined) {
      this.subscribeToSaveResponse(this.dayService.update(day));
    } else {
      this.subscribeToSaveResponse(this.dayService.create(day));
    }
  }

  private createFromForm(): IDay {
    return {
      ...new Day(),
      id: this.editForm.get(['id']).value,
      nameDay: this.editForm.get(['nameDay']).value,
      stateDay: this.editForm.get(['stateDay']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDay>>) {
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
