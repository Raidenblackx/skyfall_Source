import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IModality, Modality } from 'app/shared/model/modality.model';
import { ModalityService } from './modality.service';

@Component({
  selector: 'jhi-modality-update',
  templateUrl: './modality-update.component.html'
})
export class ModalityUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    nameModality: [null, [Validators.required, Validators.maxLength(40)]],
    color: [null, [Validators.required, Validators.maxLength(50)]],
    stateModality: [null, [Validators.required]]
  });

  constructor(protected modalityService: ModalityService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ modality }) => {
      this.updateForm(modality);
    });
  }

  updateForm(modality: IModality) {
    this.editForm.patchValue({
      id: modality.id,
      nameModality: modality.nameModality,
      color: modality.color,
      stateModality: modality.stateModality
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const modality = this.createFromForm();
    if (modality.id !== undefined) {
      this.subscribeToSaveResponse(this.modalityService.update(modality));
    } else {
      this.subscribeToSaveResponse(this.modalityService.create(modality));
    }
  }

  private createFromForm(): IModality {
    return {
      ...new Modality(),
      id: this.editForm.get(['id']).value,
      nameModality: this.editForm.get(['nameModality']).value,
      color: this.editForm.get(['color']).value,
      stateModality: this.editForm.get(['stateModality']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IModality>>) {
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
