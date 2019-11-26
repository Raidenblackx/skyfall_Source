import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IJourneyInstructor, JourneyInstructor } from 'app/shared/model/journey-instructor.model';
import { JourneyInstructorService } from './journey-instructor.service';

@Component({
  selector: 'jhi-journey-instructor-update',
  templateUrl: './journey-instructor-update.component.html'
})
export class JourneyInstructorUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    nameDay: [null, [Validators.required, Validators.maxLength(40)]],
    description: [null, [Validators.required, Validators.maxLength(200)]],
    stateJourneyInstructor: [null, [Validators.required]]
  });

  constructor(
    protected journeyInstructorService: JourneyInstructorService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ journeyInstructor }) => {
      this.updateForm(journeyInstructor);
    });
  }

  updateForm(journeyInstructor: IJourneyInstructor) {
    this.editForm.patchValue({
      id: journeyInstructor.id,
      nameDay: journeyInstructor.nameDay,
      description: journeyInstructor.description,
      stateJourneyInstructor: journeyInstructor.stateJourneyInstructor
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const journeyInstructor = this.createFromForm();
    if (journeyInstructor.id !== undefined) {
      this.subscribeToSaveResponse(this.journeyInstructorService.update(journeyInstructor));
    } else {
      this.subscribeToSaveResponse(this.journeyInstructorService.create(journeyInstructor));
    }
  }

  private createFromForm(): IJourneyInstructor {
    return {
      ...new JourneyInstructor(),
      id: this.editForm.get(['id']).value,
      nameDay: this.editForm.get(['nameDay']).value,
      description: this.editForm.get(['description']).value,
      stateJourneyInstructor: this.editForm.get(['stateJourneyInstructor']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IJourneyInstructor>>) {
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
