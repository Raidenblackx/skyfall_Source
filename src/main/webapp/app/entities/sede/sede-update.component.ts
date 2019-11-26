import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { ISede, Sede } from 'app/shared/model/sede.model';
import { SedeService } from './sede.service';

@Component({
  selector: 'jhi-sede-update',
  templateUrl: './sede-update.component.html'
})
export class SedeUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    nameSede: [null, [Validators.required, Validators.maxLength(50)]],
    description: [null, [Validators.required, Validators.maxLength(400)]],
    stateSede: [null, [Validators.required]]
  });

  constructor(protected sedeService: SedeService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ sede }) => {
      this.updateForm(sede);
    });
  }

  updateForm(sede: ISede) {
    this.editForm.patchValue({
      id: sede.id,
      nameSede: sede.nameSede,
      description: sede.description,
      stateSede: sede.stateSede
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const sede = this.createFromForm();
    if (sede.id !== undefined) {
      this.subscribeToSaveResponse(this.sedeService.update(sede));
    } else {
      this.subscribeToSaveResponse(this.sedeService.create(sede));
    }
  }

  private createFromForm(): ISede {
    return {
      ...new Sede(),
      id: this.editForm.get(['id']).value,
      nameSede: this.editForm.get(['nameSede']).value,
      description: this.editForm.get(['description']).value,
      stateSede: this.editForm.get(['stateSede']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISede>>) {
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
