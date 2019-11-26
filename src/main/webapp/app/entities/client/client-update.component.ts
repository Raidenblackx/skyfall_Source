import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';
import { IClient, Client } from 'app/shared/model/client.model';
import { ClientService } from './client.service';
import { IDocumentType } from 'app/shared/model/document-type.model';
import { DocumentTypeService } from 'app/entities/document-type/document-type.service';
import { IUser } from 'app/core/user/user.model';
import { UserService } from 'app/core/user/user.service';

@Component({
  selector: 'jhi-client-update',
  templateUrl: './client-update.component.html'
})
export class ClientUpdateComponent implements OnInit {
  isSaving: boolean;

  documenttypes: IDocumentType[];

  users: IUser[];

  editForm = this.fb.group({
    id: [],
    documentNumber: [null, [Validators.required]],
    firstName: [null, [Validators.required, Validators.maxLength(50)]],
    secondName: [null, [Validators.maxLength(50)]],
    firstLastName: [null, [Validators.required, Validators.maxLength(50)]],
    secondLastName: [null, [Validators.maxLength(50)]],
    documentTypeId: [null, Validators.required],
    userId: [null, Validators.required]
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected clientService: ClientService,
    protected documentTypeService: DocumentTypeService,
    protected userService: UserService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ client }) => {
      this.updateForm(client);
    });
    this.documentTypeService
      .query()
      .subscribe(
        (res: HttpResponse<IDocumentType[]>) => (this.documenttypes = res.body),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
    this.userService
      .query()
      .subscribe((res: HttpResponse<IUser[]>) => (this.users = res.body), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(client: IClient) {
    this.editForm.patchValue({
      id: client.id,
      documentNumber: client.documentNumber,
      firstName: client.firstName,
      secondName: client.secondName,
      firstLastName: client.firstLastName,
      secondLastName: client.secondLastName,
      documentTypeId: client.documentTypeId,
      userId: client.userId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const client = this.createFromForm();
    if (client.id !== undefined) {
      this.subscribeToSaveResponse(this.clientService.update(client));
    } else {
      this.subscribeToSaveResponse(this.clientService.create(client));
    }
  }

  private createFromForm(): IClient {
    return {
      ...new Client(),
      id: this.editForm.get(['id']).value,
      documentNumber: this.editForm.get(['documentNumber']).value,
      firstName: this.editForm.get(['firstName']).value,
      secondName: this.editForm.get(['secondName']).value,
      firstLastName: this.editForm.get(['firstLastName']).value,
      secondLastName: this.editForm.get(['secondLastName']).value,
      documentTypeId: this.editForm.get(['documentTypeId']).value,
      userId: this.editForm.get(['userId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IClient>>) {
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

  trackDocumentTypeById(index: number, item: IDocumentType) {
    return item.id;
  }

  trackUserById(index: number, item: IUser) {
    return item.id;
  }
}
