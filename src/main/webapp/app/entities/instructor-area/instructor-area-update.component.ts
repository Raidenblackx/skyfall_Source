import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';
import { IInstructorArea, InstructorArea } from 'app/shared/model/instructor-area.model';
import { InstructorAreaService } from './instructor-area.service';
import { IArea } from 'app/shared/model/area.model';
import { AreaService } from 'app/entities/area/area.service';
import { IInstructor } from 'app/shared/model/instructor.model';
import { InstructorService } from 'app/entities/instructor/instructor.service';

@Component({
  selector: 'jhi-instructor-area-update',
  templateUrl: './instructor-area-update.component.html'
})
export class InstructorAreaUpdateComponent implements OnInit {
  isSaving: boolean;

  areas: IArea[];

  instructors: IInstructor[];

  editForm = this.fb.group({
    id: [],
    stateInstructorArea: [null, [Validators.required]],
    areaId: [null, Validators.required],
    instructorId: [null, Validators.required]
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected instructorAreaService: InstructorAreaService,
    protected areaService: AreaService,
    protected instructorService: InstructorService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ instructorArea }) => {
      this.updateForm(instructorArea);
    });
    this.areaService
      .query()
      .subscribe((res: HttpResponse<IArea[]>) => (this.areas = res.body), (res: HttpErrorResponse) => this.onError(res.message));
    this.instructorService
      .query()
      .subscribe(
        (res: HttpResponse<IInstructor[]>) => (this.instructors = res.body),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  updateForm(instructorArea: IInstructorArea) {
    this.editForm.patchValue({
      id: instructorArea.id,
      stateInstructorArea: instructorArea.stateInstructorArea,
      areaId: instructorArea.areaId,
      instructorId: instructorArea.instructorId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const instructorArea = this.createFromForm();
    if (instructorArea.id !== undefined) {
      this.subscribeToSaveResponse(this.instructorAreaService.update(instructorArea));
    } else {
      this.subscribeToSaveResponse(this.instructorAreaService.create(instructorArea));
    }
  }

  private createFromForm(): IInstructorArea {
    return {
      ...new InstructorArea(),
      id: this.editForm.get(['id']).value,
      stateInstructorArea: this.editForm.get(['stateInstructorArea']).value,
      areaId: this.editForm.get(['areaId']).value,
      instructorId: this.editForm.get(['instructorId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IInstructorArea>>) {
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

  trackAreaById(index: number, item: IArea) {
    return item.id;
  }

  trackInstructorById(index: number, item: IInstructor) {
    return item.id;
  }
}
