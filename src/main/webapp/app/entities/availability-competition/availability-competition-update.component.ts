import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';
import { IAvailabilityCompetition, AvailabilityCompetition } from 'app/shared/model/availability-competition.model';
import { AvailabilityCompetitionService } from './availability-competition.service';
import { ICompetition } from 'app/shared/model/competition.model';
import { CompetitionService } from 'app/entities/competition/competition.service';
import { IInstructorLinking } from 'app/shared/model/instructor-linking.model';
import { InstructorLinkingService } from 'app/entities/instructor-linking/instructor-linking.service';

@Component({
  selector: 'jhi-availability-competition-update',
  templateUrl: './availability-competition-update.component.html'
})
export class AvailabilityCompetitionUpdateComponent implements OnInit {
  isSaving: boolean;

  competitions: ICompetition[];

  instructorlinkings: IInstructorLinking[];

  editForm = this.fb.group({
    id: [],
    competitionId: [null, Validators.required],
    instructorLinkingId: [null, Validators.required]
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected availabilityCompetitionService: AvailabilityCompetitionService,
    protected competitionService: CompetitionService,
    protected instructorLinkingService: InstructorLinkingService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ availabilityCompetition }) => {
      this.updateForm(availabilityCompetition);
    });
    this.competitionService
      .query()
      .subscribe(
        (res: HttpResponse<ICompetition[]>) => (this.competitions = res.body),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
    this.instructorLinkingService
      .query()
      .subscribe(
        (res: HttpResponse<IInstructorLinking[]>) => (this.instructorlinkings = res.body),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  updateForm(availabilityCompetition: IAvailabilityCompetition) {
    this.editForm.patchValue({
      id: availabilityCompetition.id,
      competitionId: availabilityCompetition.competitionId,
      instructorLinkingId: availabilityCompetition.instructorLinkingId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const availabilityCompetition = this.createFromForm();
    if (availabilityCompetition.id !== undefined) {
      this.subscribeToSaveResponse(this.availabilityCompetitionService.update(availabilityCompetition));
    } else {
      this.subscribeToSaveResponse(this.availabilityCompetitionService.create(availabilityCompetition));
    }
  }

  private createFromForm(): IAvailabilityCompetition {
    return {
      ...new AvailabilityCompetition(),
      id: this.editForm.get(['id']).value,
      competitionId: this.editForm.get(['competitionId']).value,
      instructorLinkingId: this.editForm.get(['instructorLinkingId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAvailabilityCompetition>>) {
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

  trackCompetitionById(index: number, item: ICompetition) {
    return item.id;
  }

  trackInstructorLinkingById(index: number, item: IInstructorLinking) {
    return item.id;
  }
}
