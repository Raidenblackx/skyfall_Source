import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITrimesterPlanning } from 'app/shared/model/trimester-planning.model';
import { TrimesterPlanningService } from './trimester-planning.service';

@Component({
  templateUrl: './trimester-planning-delete-dialog.component.html'
})
export class TrimesterPlanningDeleteDialogComponent {
  trimesterPlanning: ITrimesterPlanning;

  constructor(
    protected trimesterPlanningService: TrimesterPlanningService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.trimesterPlanningService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'trimesterPlanningListModification',
        content: 'Deleted an trimesterPlanning'
      });
      this.activeModal.dismiss(true);
    });
  }
}
