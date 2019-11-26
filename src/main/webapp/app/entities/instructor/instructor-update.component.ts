import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';
import { IInstructor, Instructor } from 'app/shared/model/instructor.model';
import { InstructorService } from './instructor.service';
import { IClient } from 'app/shared/model/client.model';
import { ClientService } from 'app/entities/client/client.service';

@Component({
  selector: 'jhi-instructor-update',
  templateUrl: './instructor-update.component.html'
})
export class InstructorUpdateComponent implements OnInit {
  isSaving: boolean;

  clients: IClient[];

  editForm = this.fb.group({
    id: [],
    stateInstructor: [null, [Validators.required]],
    clientId: [null, Validators.required]
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected instructorService: InstructorService,
    protected clientService: ClientService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ instructor }) => {
      this.updateForm(instructor);
    });
    this.clientService
      .query()
      .subscribe((res: HttpResponse<IClient[]>) => (this.clients = res.body), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(instructor: IInstructor) {
    this.editForm.patchValue({
      id: instructor.id,
      stateInstructor: instructor.stateInstructor,
      clientId: instructor.clientId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const instructor = this.createFromForm();
    if (instructor.id !== undefined) {
      this.subscribeToSaveResponse(this.instructorService.update(instructor));
    } else {
      this.subscribeToSaveResponse(this.instructorService.create(instructor));
    }
  }

  private createFromForm(): IInstructor {
    return {
      ...new Instructor(),
      id: this.editForm.get(['id']).value,
      stateInstructor: this.editForm.get(['stateInstructor']).value,
      clientId: this.editForm.get(['clientId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IInstructor>>) {
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

  trackClientById(index: number, item: IClient) {
    return item.id;
  }
}
