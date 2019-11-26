import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';
import { ICompetition, Competition } from 'app/shared/model/competition.model';
import { CompetitionService } from './competition.service';
import { IProgram } from 'app/shared/model/program.model';
import { ProgramService } from 'app/entities/program/program.service';

@Component({
  selector: 'jhi-competition-update',
  templateUrl: './competition-update.component.html'
})
export class CompetitionUpdateComponent implements OnInit {
  isSaving: boolean;

  programs: IProgram[];

  editForm = this.fb.group({
    id: [],
    codeCompetition: [null, [Validators.required, Validators.maxLength(50)]],
    denomination: [null, [Validators.required, Validators.maxLength(1000)]],
    programId: [null, Validators.required]
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected competitionService: CompetitionService,
    protected programService: ProgramService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ competition }) => {
      this.updateForm(competition);
    });
    this.programService
      .query()
      .subscribe((res: HttpResponse<IProgram[]>) => (this.programs = res.body), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(competition: ICompetition) {
    this.editForm.patchValue({
      id: competition.id,
      codeCompetition: competition.codeCompetition,
      denomination: competition.denomination,
      programId: competition.programId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const competition = this.createFromForm();
    if (competition.id !== undefined) {
      this.subscribeToSaveResponse(this.competitionService.update(competition));
    } else {
      this.subscribeToSaveResponse(this.competitionService.create(competition));
    }
  }

  private createFromForm(): ICompetition {
    return {
      ...new Competition(),
      id: this.editForm.get(['id']).value,
      codeCompetition: this.editForm.get(['codeCompetition']).value,
      denomination: this.editForm.get(['denomination']).value,
      programId: this.editForm.get(['programId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICompetition>>) {
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
