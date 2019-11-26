import { Component, OnInit, ElementRef } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IWorkingDay, WorkingDay } from 'app/shared/model/working-day.model';
import { WorkingDayService } from './working-day.service';

@Component({
  selector: 'jhi-working-day-update',
  templateUrl: './working-day-update.component.html'
})
export class WorkingDayUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    initialWorkingDay: [null, [Validators.required, Validators.maxLength(20)]],
    nameWorkingDay: [null, [Validators.required, Validators.maxLength(40)]],
    description: [null, [Validators.required, Validators.maxLength(100)]],
    imagenUrl: [],
    imagenUrlContentType: [],
    stateWorkingDay: [null, [Validators.required]]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected workingDayService: WorkingDayService,
    protected elementRef: ElementRef,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ workingDay }) => {
      this.updateForm(workingDay);
    });
  }

  updateForm(workingDay: IWorkingDay) {
    this.editForm.patchValue({
      id: workingDay.id,
      initialWorkingDay: workingDay.initialWorkingDay,
      nameWorkingDay: workingDay.nameWorkingDay,
      description: workingDay.description,
      imagenUrl: workingDay.imagenUrl,
      imagenUrlContentType: workingDay.imagenUrlContentType,
      stateWorkingDay: workingDay.stateWorkingDay
    });
  }

  byteSize(field) {
    return this.dataUtils.byteSize(field);
  }

  openFile(contentType, field) {
    return this.dataUtils.openFile(contentType, field);
  }

  setFileData(event, field: string, isImage) {
    return new Promise((resolve, reject) => {
      if (event && event.target && event.target.files && event.target.files[0]) {
        const file: File = event.target.files[0];
        if (isImage && !file.type.startsWith('image/')) {
          reject(`File was expected to be an image but was found to be ${file.type}`);
        } else {
          const filedContentType: string = field + 'ContentType';
          this.dataUtils.toBase64(file, base64Data => {
            this.editForm.patchValue({
              [field]: base64Data,
              [filedContentType]: file.type
            });
          });
        }
      } else {
        reject(`Base64 data was not set as file could not be extracted from passed parameter: ${event}`);
      }
    }).then(
      // eslint-disable-next-line no-console
      () => console.log('blob added'), // success
      this.onError
    );
  }

  clearInputImage(field: string, fieldContentType: string, idInput: string) {
    this.editForm.patchValue({
      [field]: null,
      [fieldContentType]: null
    });
    if (this.elementRef && idInput && this.elementRef.nativeElement.querySelector('#' + idInput)) {
      this.elementRef.nativeElement.querySelector('#' + idInput).value = null;
    }
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const workingDay = this.createFromForm();
    if (workingDay.id !== undefined) {
      this.subscribeToSaveResponse(this.workingDayService.update(workingDay));
    } else {
      this.subscribeToSaveResponse(this.workingDayService.create(workingDay));
    }
  }

  private createFromForm(): IWorkingDay {
    return {
      ...new WorkingDay(),
      id: this.editForm.get(['id']).value,
      initialWorkingDay: this.editForm.get(['initialWorkingDay']).value,
      nameWorkingDay: this.editForm.get(['nameWorkingDay']).value,
      description: this.editForm.get(['description']).value,
      imagenUrlContentType: this.editForm.get(['imagenUrlContentType']).value,
      imagenUrl: this.editForm.get(['imagenUrl']).value,
      stateWorkingDay: this.editForm.get(['stateWorkingDay']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IWorkingDay>>) {
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
}
