import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ILevelFormation } from 'app/shared/model/level-formation.model';
import { LevelFormationService } from './level-formation.service';

@Component({
  templateUrl: './level-formation-delete-dialog.component.html'
})
export class LevelFormationDeleteDialogComponent {
  levelFormation: ILevelFormation;

  constructor(
    protected levelFormationService: LevelFormationService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.levelFormationService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'levelFormationListModification',
        content: 'Deleted an levelFormation'
      });
      this.activeModal.dismiss(true);
    });
  }
}
