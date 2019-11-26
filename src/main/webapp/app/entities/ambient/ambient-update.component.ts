import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';
import { IAmbient, Ambient } from 'app/shared/model/ambient.model';
import { AmbientService } from './ambient.service';
import { ITypeEnvironment } from 'app/shared/model/type-environment.model';
import { TypeEnvironmentService } from 'app/entities/type-environment/type-environment.service';
import { ISede } from 'app/shared/model/sede.model';
import { SedeService } from 'app/entities/sede/sede.service';

@Component({
  selector: 'jhi-ambient-update',
  templateUrl: './ambient-update.component.html'
})
export class AmbientUpdateComponent implements OnInit {
  isSaving: boolean;

  typeenvironments: ITypeEnvironment[];

  sedes: ISede[];

  editForm = this.fb.group({
    id: [],
    numberRoom: [null, [Validators.required, Validators.maxLength(50)]],
    description: [null, [Validators.required, Validators.maxLength(1000)]],
    limitation: [null, [Validators.required]],
    stateAmbient: [null, [Validators.required]],
    typeEnvironmentId: [null, Validators.required],
    sedeId: [null, Validators.required]
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected ambientService: AmbientService,
    protected typeEnvironmentService: TypeEnvironmentService,
    protected sedeService: SedeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ ambient }) => {
      this.updateForm(ambient);
    });
    this.typeEnvironmentService
      .query()
      .subscribe(
        (res: HttpResponse<ITypeEnvironment[]>) => (this.typeenvironments = res.body),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
    this.sedeService
      .query()
      .subscribe((res: HttpResponse<ISede[]>) => (this.sedes = res.body), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(ambient: IAmbient) {
    this.editForm.patchValue({
      id: ambient.id,
      numberRoom: ambient.numberRoom,
      description: ambient.description,
      limitation: ambient.limitation,
      stateAmbient: ambient.stateAmbient,
      typeEnvironmentId: ambient.typeEnvironmentId,
      sedeId: ambient.sedeId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const ambient = this.createFromForm();
    if (ambient.id !== undefined) {
      this.subscribeToSaveResponse(this.ambientService.update(ambient));
    } else {
      this.subscribeToSaveResponse(this.ambientService.create(ambient));
    }
  }

  private createFromForm(): IAmbient {
    return {
      ...new Ambient(),
      id: this.editForm.get(['id']).value,
      numberRoom: this.editForm.get(['numberRoom']).value,
      description: this.editForm.get(['description']).value,
      limitation: this.editForm.get(['limitation']).value,
      stateAmbient: this.editForm.get(['stateAmbient']).value,
      typeEnvironmentId: this.editForm.get(['typeEnvironmentId']).value,
      sedeId: this.editForm.get(['sedeId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAmbient>>) {
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

  trackTypeEnvironmentById(index: number, item: ITypeEnvironment) {
    return item.id;
  }

  trackSedeById(index: number, item: ISede) {
    return item.id;
  }
}
