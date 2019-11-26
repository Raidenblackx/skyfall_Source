import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IYear, Year } from 'app/shared/model/year.model';
import { YearService } from './year.service';

@Component({
  selector: 'jhi-year-update',
  templateUrl: './year-update.component.html'
})
export class YearUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    numberYear: [null, [Validators.required]],
    stateYear: [null, [Validators.required]]
  });

  constructor(protected yearService: YearService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ year }) => {
      this.updateForm(year);
    });
  }

  updateForm(year: IYear) {
    this.editForm.patchValue({
      id: year.id,
      numberYear: year.numberYear,
      stateYear: year.stateYear
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const year = this.createFromForm();
    if (year.id !== undefined) {
      this.subscribeToSaveResponse(this.yearService.update(year));
    } else {
      this.subscribeToSaveResponse(this.yearService.create(year));
    }
  }

  private createFromForm(): IYear {
    return {
      ...new Year(),
      id: this.editForm.get(['id']).value,
      numberYear: this.editForm.get(['numberYear']).value,
      stateYear: this.editForm.get(['stateYear']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IYear>>) {
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
