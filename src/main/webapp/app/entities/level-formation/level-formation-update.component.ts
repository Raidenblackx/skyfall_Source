import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { ILevelFormation, LevelFormation } from 'app/shared/model/level-formation.model';
import { LevelFormationService } from './level-formation.service';

@Component({
  selector: 'jhi-level-formation-update',
  templateUrl: './level-formation-update.component.html'
})
export class LevelFormationUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    level: [null, [Validators.required, Validators.maxLength(40)]],
    stateLevelFormation: [null, [Validators.required]]
  });

  constructor(protected levelFormationService: LevelFormationService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ levelFormation }) => {
      this.updateForm(levelFormation);
    });
  }

  updateForm(levelFormation: ILevelFormation) {
    this.editForm.patchValue({
      id: levelFormation.id,
      level: levelFormation.level,
      stateLevelFormation: levelFormation.stateLevelFormation
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const levelFormation = this.createFromForm();
    if (levelFormation.id !== undefined) {
      this.subscribeToSaveResponse(this.levelFormationService.update(levelFormation));
    } else {
      this.subscribeToSaveResponse(this.levelFormationService.create(levelFormation));
    }
  }

  private createFromForm(): ILevelFormation {
    return {
      ...new LevelFormation(),
      id: this.editForm.get(['id']).value,
      level: this.editForm.get(['level']).value,
      stateLevelFormation: this.editForm.get(['stateLevelFormation']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ILevelFormation>>) {
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
