import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICompetition } from 'app/shared/model/competition.model';
import { CompetitionService } from './competition.service';

@Component({
  templateUrl: './competition-delete-dialog.component.html'
})
export class CompetitionDeleteDialogComponent {
  competition: ICompetition;

  constructor(
    protected competitionService: CompetitionService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.competitionService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'competitionListModification',
        content: 'Deleted an competition'
      });
      this.activeModal.dismiss(true);
    });
  }
}
