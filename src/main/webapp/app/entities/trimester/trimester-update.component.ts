import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';
import { ITrimester, Trimester } from 'app/shared/model/trimester.model';
import { TrimesterService } from './trimester.service';
import { IWorkingDay } from 'app/shared/model/working-day.model';
import { WorkingDayService } from 'app/entities/working-day/working-day.service';
import { ILevelFormation } from 'app/shared/model/level-formation.model';
import { LevelFormationService } from 'app/entities/level-formation/level-formation.service';

@Component({
  selector: 'jhi-trimester-update',
  templateUrl: './trimester-update.component.html'
})
export class TrimesterUpdateComponent implements OnInit {
  isSaving: boolean;

  workingdays: IWorkingDay[];

  levelformations: ILevelFormation[];

  editForm = this.fb.group({
    id: [],
    nameTrimester: [null, [Validators.required]],
    stateTrimester: [null, [Validators.required]],
    workingDayId: [null, Validators.required],
    levelFormationId: [null, Validators.required]
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected trimesterService: TrimesterService,
    protected workingDayService: WorkingDayService,
    protected levelFormationService: LevelFormationService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ trimester }) => {
      this.updateForm(trimester);
    });
    this.workingDayService
      .query()
      .subscribe(
        (res: HttpResponse<IWorkingDay[]>) => (this.workingdays = res.body),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
    this.levelFormationService
      .query()
      .subscribe(
        (res: HttpResponse<ILevelFormation[]>) => (this.levelformations = res.body),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  updateForm(trimester: ITrimester) {
    this.editForm.patchValue({
      id: trimester.id,
      nameTrimester: trimester.nameTrimester,
      stateTrimester: trimester.stateTrimester,
      workingDayId: trimester.workingDayId,
      levelFormationId: trimester.levelFormationId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const trimester = this.createFromForm();
    if (trimester.id !== undefined) {
      this.subscribeToSaveResponse(this.trimesterService.update(trimester));
    } else {
      this.subscribeToSaveResponse(this.trimesterService.create(trimester));
    }
  }

  private createFromForm(): ITrimester {
    return {
      ...new Trimester(),
      id: this.editForm.get(['id']).value,
      nameTrimester: this.editForm.get(['nameTrimester']).value,
      stateTrimester: this.editForm.get(['stateTrimester']).value,
      workingDayId: this.editForm.get(['workingDayId']).value,
      levelFormationId: this.editForm.get(['levelFormationId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITrimester>>) {
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

  trackWorkingDayById(index: number, item: IWorkingDay) {
    return item.id;
  }

  trackLevelFormationById(index: number, item: ILevelFormation) {
    return item.id;
  }
}
