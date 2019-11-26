import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITrimester } from 'app/shared/model/trimester.model';
import { TrimesterService } from './trimester.service';

@Component({
  templateUrl: './trimester-delete-dialog.component.html'
})
export class TrimesterDeleteDialogComponent {
  trimester: ITrimester;

  constructor(protected trimesterService: TrimesterService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.trimesterService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'trimesterListModification',
        content: 'Deleted an trimester'
      });
      this.activeModal.dismiss(true);
    });
  }
}
