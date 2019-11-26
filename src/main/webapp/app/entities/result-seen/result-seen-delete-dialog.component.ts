import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IResultSeen } from 'app/shared/model/result-seen.model';
import { ResultSeenService } from './result-seen.service';

@Component({
  templateUrl: './result-seen-delete-dialog.component.html'
})
export class ResultSeenDeleteDialogComponent {
  resultSeen: IResultSeen;

  constructor(
    protected resultSeenService: ResultSeenService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.resultSeenService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'resultSeenListModification',
        content: 'Deleted an resultSeen'
      });
      this.activeModal.dismiss(true);
    });
  }
}
