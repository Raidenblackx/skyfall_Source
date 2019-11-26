import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAvailabilityCompetition } from 'app/shared/model/availability-competition.model';
import { AvailabilityCompetitionService } from './availability-competition.service';

@Component({
  templateUrl: './availability-competition-delete-dialog.component.html'
})
export class AvailabilityCompetitionDeleteDialogComponent {
  availabilityCompetition: IAvailabilityCompetition;

  constructor(
    protected availabilityCompetitionService: AvailabilityCompetitionService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.availabilityCompetitionService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'availabilityCompetitionListModification',
        content: 'Deleted an availabilityCompetition'
      });
      this.activeModal.dismiss(true);
    });
  }
}
