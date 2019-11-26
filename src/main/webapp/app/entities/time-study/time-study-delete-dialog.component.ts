import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITimeStudy } from 'app/shared/model/time-study.model';
import { TimeStudyService } from './time-study.service';

@Component({
  templateUrl: './time-study-delete-dialog.component.html'
})
export class TimeStudyDeleteDialogComponent {
  timeStudy: ITimeStudy;

  constructor(protected timeStudyService: TimeStudyService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.timeStudyService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'timeStudyListModification',
        content: 'Deleted an timeStudy'
      });
      this.activeModal.dismiss(true);
    });
  }
}
