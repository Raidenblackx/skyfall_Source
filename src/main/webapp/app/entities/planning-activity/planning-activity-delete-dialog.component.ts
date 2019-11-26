import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPlanningActivity } from 'app/shared/model/planning-activity.model';
import { PlanningActivityService } from './planning-activity.service';

@Component({
  templateUrl: './planning-activity-delete-dialog.component.html'
})
export class PlanningActivityDeleteDialogComponent {
  planningActivity: IPlanningActivity;

  constructor(
    protected planningActivityService: PlanningActivityService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.planningActivityService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'planningActivityListModification',
        content: 'Deleted an planningActivity'
      });
      this.activeModal.dismiss(true);
    });
  }
}
