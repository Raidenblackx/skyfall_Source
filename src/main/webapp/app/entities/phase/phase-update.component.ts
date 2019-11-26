import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';
import { IPhase, Phase } from 'app/shared/model/phase.model';
import { PhaseService } from './phase.service';
import { IProyect } from 'app/shared/model/proyect.model';
import { ProyectService } from 'app/entities/proyect/proyect.service';

@Component({
  selector: 'jhi-phase-update',
  templateUrl: './phase-update.component.html'
})
export class PhaseUpdateComponent implements OnInit {
  isSaving: boolean;

  proyects: IProyect[];

  editForm = this.fb.group({
    id: [],
    namePhase: [null, [Validators.required, Validators.maxLength(40)]],
    statePhase: [null, [Validators.required]],
    proyectId: [null, Validators.required]
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected phaseService: PhaseService,
    protected proyectService: ProyectService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ phase }) => {
      this.updateForm(phase);
    });
    this.proyectService
      .query()
      .subscribe((res: HttpResponse<IProyect[]>) => (this.proyects = res.body), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(phase: IPhase) {
    this.editForm.patchValue({
      id: phase.id,
      namePhase: phase.namePhase,
      statePhase: phase.statePhase,
      proyectId: phase.proyectId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const phase = this.createFromForm();
    if (phase.id !== undefined) {
      this.subscribeToSaveResponse(this.phaseService.update(phase));
    } else {
      this.subscribeToSaveResponse(this.phaseService.create(phase));
    }
  }

  private createFromForm(): IPhase {
    return {
      ...new Phase(),
      id: this.editForm.get(['id']).value,
      namePhase: this.editForm.get(['namePhase']).value,
      statePhase: this.editForm.get(['statePhase']).value,
      proyectId: this.editForm.get(['proyectId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPhase>>) {
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

  trackProyectById(index: number, item: IProyect) {
    return item.id;
  }
}
