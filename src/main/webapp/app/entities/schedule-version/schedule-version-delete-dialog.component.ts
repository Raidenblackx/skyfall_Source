import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IScheduleVersion } from 'app/shared/model/schedule-version.model';
import { ScheduleVersionService } from './schedule-version.service';

@Component({
  templateUrl: './schedule-version-delete-dialog.component.html'
})
export class ScheduleVersionDeleteDialogComponent {
  scheduleVersion: IScheduleVersion;

  constructor(
    protected scheduleVersionService: ScheduleVersionService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.scheduleVersionService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'scheduleVersionListModification',
        content: 'Deleted an scheduleVersion'
      });
      this.activeModal.dismiss(true);
    });
  }
}
