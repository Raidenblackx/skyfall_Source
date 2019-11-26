import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';
import { IInstructorLinking, InstructorLinking } from 'app/shared/model/instructor-linking.model';
import { InstructorLinkingService } from './instructor-linking.service';
import { IYear } from 'app/shared/model/year.model';
import { YearService } from 'app/entities/year/year.service';
import { IInstructor } from 'app/shared/model/instructor.model';
import { InstructorService } from 'app/entities/instructor/instructor.service';
import { ILinkage } from 'app/shared/model/linkage.model';
import { LinkageService } from 'app/entities/linkage/linkage.service';

@Component({
  selector: 'jhi-instructor-linking-update',
  templateUrl: './instructor-linking-update.component.html'
})
export class InstructorLinkingUpdateComponent implements OnInit {
  isSaving: boolean;

  years: IYear[];

  instructors: IInstructor[];

  linkages: ILinkage[];

  editForm = this.fb.group({
    id: [],
    startDate: [null, [Validators.required]],
    endDate: [null, [Validators.required]],
    yearId: [null, Validators.required],
    instructorId: [null, Validators.required],
    linkageId: [null, Validators.required]
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected instructorLinkingService: InstructorLinkingService,
    protected yearService: YearService,
    protected instructorService: InstructorService,
    protected linkageService: LinkageService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ instructorLinking }) => {
      this.updateForm(instructorLinking);
    });
    this.yearService
      .query()
      .subscribe((res: HttpResponse<IYear[]>) => (this.years = res.body), (res: HttpErrorResponse) => this.onError(res.message));
    this.instructorService
      .query()
      .subscribe(
        (res: HttpResponse<IInstructor[]>) => (this.instructors = res.body),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
    this.linkageService
      .query()
      .subscribe((res: HttpResponse<ILinkage[]>) => (this.linkages = res.body), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(instructorLinking: IInstructorLinking) {
    this.editForm.patchValue({
      id: instructorLinking.id,
      startDate: instructorLinking.startDate != null ? instructorLinking.startDate.format(DATE_TIME_FORMAT) : null,
      endDate: instructorLinking.endDate != null ? instructorLinking.endDate.format(DATE_TIME_FORMAT) : null,
      yearId: instructorLinking.yearId,
      instructorId: instructorLinking.instructorId,
      linkageId: instructorLinking.linkageId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const instructorLinking = this.createFromForm();
    if (instructorLinking.id !== undefined) {
      this.subscribeToSaveResponse(this.instructorLinkingService.update(instructorLinking));
    } else {
      this.subscribeToSaveResponse(this.instructorLinkingService.create(instructorLinking));
    }
  }

  private createFromForm(): IInstructorLinking {
    return {
      ...new InstructorLinking(),
      id: this.editForm.get(['id']).value,
      startDate:
        this.editForm.get(['startDate']).value != null ? moment(this.editForm.get(['startDate']).value, DATE_TIME_FORMAT) : undefined,
      endDate: this.editForm.get(['endDate']).value != null ? moment(this.editForm.get(['endDate']).value, DATE_TIME_FORMAT) : undefined,
      yearId: this.editForm.get(['yearId']).value,
      instructorId: this.editForm.get(['instructorId']).value,
      linkageId: this.editForm.get(['linkageId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IInstructorLinking>>) {
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

  trackYearById(index: number, item: IYear) {
    return item.id;
  }

  trackInstructorById(index: number, item: IInstructor) {
    return item.id;
  }

  trackLinkageById(index: number, item: ILinkage) {
    return item.id;
  }
}
