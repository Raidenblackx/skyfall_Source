import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';
import { IProyect, Proyect } from 'app/shared/model/proyect.model';
import { ProyectService } from './proyect.service';
import { IProgram } from 'app/shared/model/program.model';
import { ProgramService } from 'app/entities/program/program.service';

@Component({
  selector: 'jhi-proyect-update',
  templateUrl: './proyect-update.component.html'
})
export class ProyectUpdateComponent implements OnInit {
  isSaving: boolean;

  programs: IProgram[];

  editForm = this.fb.group({
    id: [],
    codeProyect: [null, [Validators.required, Validators.maxLength(40)]],
    nameProyect: [null, [Validators.required, Validators.maxLength(500)]],
    stateProyect: [null, [Validators.required]],
    programId: [null, Validators.required]
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected proyectService: ProyectService,
    protected programService: ProgramService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ proyect }) => {
      this.updateForm(proyect);
    });
    this.programService
      .query()
      .subscribe((res: HttpResponse<IProgram[]>) => (this.programs = res.body), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(proyect: IProyect) {
    this.editForm.patchValue({
      id: proyect.id,
      codeProyect: proyect.codeProyect,
      nameProyect: proyect.nameProyect,
      stateProyect: proyect.stateProyect,
      programId: proyect.programId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const proyect = this.createFromForm();
    if (proyect.id !== undefined) {
      this.subscribeToSaveResponse(this.proyectService.update(proyect));
    } else {
      this.subscribeToSaveResponse(this.proyectService.create(proyect));
    }
  }

  private createFromForm(): IProyect {
    return {
      ...new Proyect(),
      id: this.editForm.get(['id']).value,
      codeProyect: this.editForm.get(['codeProyect']).value,
      nameProyect: this.editForm.get(['nameProyect']).value,
      stateProyect: this.editForm.get(['stateProyect']).value,
      programId: this.editForm.get(['programId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProyect>>) {
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

  trackProgramById(index: number, item: IProgram) {
    return item.id;
  }
}
