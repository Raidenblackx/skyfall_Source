import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { ILinkage, Linkage } from 'app/shared/model/linkage.model';
import { LinkageService } from './linkage.service';

@Component({
  selector: 'jhi-linkage-update',
  templateUrl: './linkage-update.component.html'
})
export class LinkageUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    typeLink: [null, [Validators.required, Validators.maxLength(40)]],
    hours: [null, [Validators.required]],
    stateLink: [null, [Validators.required]]
  });

  constructor(protected linkageService: LinkageService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ linkage }) => {
      this.updateForm(linkage);
    });
  }

  updateForm(linkage: ILinkage) {
    this.editForm.patchValue({
      id: linkage.id,
      typeLink: linkage.typeLink,
      hours: linkage.hours,
      stateLink: linkage.stateLink
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const linkage = this.createFromForm();
    if (linkage.id !== undefined) {
      this.subscribeToSaveResponse(this.linkageService.update(linkage));
    } else {
      this.subscribeToSaveResponse(this.linkageService.create(linkage));
    }
  }

  private createFromForm(): ILinkage {
    return {
      ...new Linkage(),
      id: this.editForm.get(['id']).value,
      typeLink: this.editForm.get(['typeLink']).value,
      hours: this.editForm.get(['hours']).value,
      stateLink: this.editForm.get(['stateLink']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ILinkage>>) {
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
