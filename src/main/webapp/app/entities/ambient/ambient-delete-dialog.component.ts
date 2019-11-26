import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAmbient } from 'app/shared/model/ambient.model';
import { AmbientService } from './ambient.service';

@Component({
  templateUrl: './ambient-delete-dialog.component.html'
})
export class AmbientDeleteDialogComponent {
  ambient: IAmbient;

  constructor(protected ambientService: AmbientService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.ambientService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'ambientListModification',
        content: 'Deleted an ambient'
      });
      this.activeModal.dismiss(true);
    });
  }
}
