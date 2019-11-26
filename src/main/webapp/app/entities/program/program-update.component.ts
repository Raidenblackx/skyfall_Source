import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';
import { IProgram, Program } from 'app/shared/model/program.model';
import { ProgramService } from './program.service';
import { ILevelFormation } from 'app/shared/model/level-formation.model';
import { LevelFormationService } from 'app/entities/level-formation/level-formation.service';

@Component({
  selector: 'jhi-program-update',
  templateUrl: './program-update.component.html'
})
export class ProgramUpdateComponent implements OnInit {
  isSaving: boolean;

  levelformations: ILevelFormation[];

  editForm = this.fb.group({
    id: [],
    codeProgram: [null, [Validators.required, Validators.maxLength(50)]],
    version: [null, [Validators.required, Validators.maxLength(40)]],
    nameProgram: [null, [Validators.required, Validators.maxLength(500)]],
    initial: [null, [Validators.required, Validators.maxLength(40)]],
    stateProgram: [null, [Validators.required]],
    levelFormationId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected programService: ProgramService,
    protected levelFormationService: LevelFormationService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ program }) => {
      this.updateForm(program);
    });
    this.levelFormationService
      .query()
      .subscribe(
        (res: HttpResponse<ILevelFormation[]>) => (this.levelformations = res.body),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  updateForm(program: IProgram) {
    this.editForm.patchValue({
      id: program.id,
      codeProgram: program.codeProgram,
      version: program.version,
      nameProgram: program.nameProgram,
      initial: program.initial,
      stateProgram: program.stateProgram,
      levelFormationId: program.levelFormationId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const program = this.createFromForm();
    if (program.id !== undefined) {
      this.subscribeToSaveResponse(this.programService.update(program));
    } else {
      this.subscribeToSaveResponse(this.programService.create(program));
    }
  }

  private createFromForm(): IProgram {
    return {
      ...new Program(),
      id: this.editForm.get(['id']).value,
      codeProgram: this.editForm.get(['codeProgram']).value,
      version: this.editForm.get(['version']).value,
      nameProgram: this.editForm.get(['nameProgram']).value,
      initial: this.editForm.get(['initial']).value,
      stateProgram: this.editForm.get(['stateProgram']).value,
      levelFormationId: this.editForm.get(['levelFormationId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProgram>>) {
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

  trackLevelFormationById(index: number, item: ILevelFormation) {
    return item.id;
  }
}
