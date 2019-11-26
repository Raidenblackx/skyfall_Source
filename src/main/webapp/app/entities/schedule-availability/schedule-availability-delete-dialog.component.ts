import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IScheduleAvailability } from 'app/shared/model/schedule-availability.model';
import { ScheduleAvailabilityService } from './schedule-availability.service';

@Component({
  templateUrl: './schedule-availability-delete-dialog.component.html'
})
export class ScheduleAvailabilityDeleteDialogComponent {
  scheduleAvailability: IScheduleAvailability;

  constructor(
    protected scheduleAvailabilityService: ScheduleAvailabilityService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.scheduleAvailabilityService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'scheduleAvailabilityListModification',
        content: 'Deleted an scheduleAvailability'
      });
      this.activeModal.dismiss(true);
    });
  }
}
