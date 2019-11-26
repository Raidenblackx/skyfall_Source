import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { ICourseState, CourseState } from 'app/shared/model/course-state.model';
import { CourseStateService } from './course-state.service';

@Component({
  selector: 'jhi-course-state-update',
  templateUrl: './course-state-update.component.html'
})
export class CourseStateUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    nameCourseState: [null, [Validators.required, Validators.maxLength(20)]],
    stateCourseState: [null, [Validators.required]]
  });

  constructor(protected courseStateService: CourseStateService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ courseState }) => {
      this.updateForm(courseState);
    });
  }

  updateForm(courseState: ICourseState) {
    this.editForm.patchValue({
      id: courseState.id,
      nameCourseState: courseState.nameCourseState,
      stateCourseState: courseState.stateCourseState
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const courseState = this.createFromForm();
    if (courseState.id !== undefined) {
      this.subscribeToSaveResponse(this.courseStateService.update(courseState));
    } else {
      this.subscribeToSaveResponse(this.courseStateService.create(courseState));
    }
  }

  private createFromForm(): ICourseState {
    return {
      ...new CourseState(),
      id: this.editForm.get(['id']).value,
      nameCourseState: this.editForm.get(['nameCourseState']).value,
      stateCourseState: this.editForm.get(['stateCourseState']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICourseState>>) {
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
