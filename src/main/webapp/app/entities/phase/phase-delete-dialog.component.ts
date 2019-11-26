import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPhase } from 'app/shared/model/phase.model';
import { PhaseService } from './phase.service';

@Component({
  templateUrl: './phase-delete-dialog.component.html'
})
export class PhaseDeleteDialogComponent {
  phase: IPhase;

  constructor(protected phaseService: PhaseService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.phaseService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'phaseListModification',
        content: 'Deleted an phase'
      });
      this.activeModal.dismiss(true);
    });
  }
}
