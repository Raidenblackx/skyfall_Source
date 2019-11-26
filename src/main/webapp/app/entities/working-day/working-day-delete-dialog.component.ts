import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IWorkingDay } from 'app/shared/model/working-day.model';
import { WorkingDayService } from './working-day.service';

@Component({
  templateUrl: './working-day-delete-dialog.component.html'
})
export class WorkingDayDeleteDialogComponent {
  workingDay: IWorkingDay;

  constructor(
    protected workingDayService: WorkingDayService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.workingDayService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'workingDayListModification',
        content: 'Deleted an workingDay'
      });
      this.activeModal.dismiss(true);
    });
  }
}
