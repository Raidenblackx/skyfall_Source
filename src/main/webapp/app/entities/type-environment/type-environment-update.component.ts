import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { ITypeEnvironment, TypeEnvironment } from 'app/shared/model/type-environment.model';
import { TypeEnvironmentService } from './type-environment.service';

@Component({
  selector: 'jhi-type-environment-update',
  templateUrl: './type-environment-update.component.html'
})
export class TypeEnvironmentUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    type: [null, [Validators.required, Validators.maxLength(50)]],
    description: [null, [Validators.required, Validators.maxLength(100)]],
    stateTypeEnvironment: [null, [Validators.required]]
  });

  constructor(
    protected typeEnvironmentService: TypeEnvironmentService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ typeEnvironment }) => {
      this.updateForm(typeEnvironment);
    });
  }

  updateForm(typeEnvironment: ITypeEnvironment) {
    this.editForm.patchValue({
      id: typeEnvironment.id,
      type: typeEnvironment.type,
      description: typeEnvironment.description,
      stateTypeEnvironment: typeEnvironment.stateTypeEnvironment
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const typeEnvironment = this.createFromForm();
    if (typeEnvironment.id !== undefined) {
      this.subscribeToSaveResponse(this.typeEnvironmentService.update(typeEnvironment));
    } else {
      this.subscribeToSaveResponse(this.typeEnvironmentService.create(typeEnvironment));
    }
  }

  private createFromForm(): ITypeEnvironment {
    return {
      ...new TypeEnvironment(),
      id: this.editForm.get(['id']).value,
      type: this.editForm.get(['type']).value,
      description: this.editForm.get(['description']).value,
      stateTypeEnvironment: this.editForm.get(['stateTypeEnvironment']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITypeEnvironment>>) {
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
