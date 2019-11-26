import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDay } from 'app/shared/model/day.model';
import { DayService } from './day.service';

@Component({
  templateUrl: './day-delete-dialog.component.html'
})
export class DayDeleteDialogComponent {
  day: IDay;

  constructor(protected dayService: DayService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.dayService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'dayListModification',
        content: 'Deleted an day'
      });
      this.activeModal.dismiss(true);
    });
  }
}
