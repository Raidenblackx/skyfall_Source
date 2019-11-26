import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IModality } from 'app/shared/model/modality.model';
import { ModalityService } from './modality.service';

@Component({
  templateUrl: './modality-delete-dialog.component.html'
})
export class ModalityDeleteDialogComponent {
  modality: IModality;

  constructor(protected modalityService: ModalityService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.modalityService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'modalityListModification',
        content: 'Deleted an modality'
      });
      this.activeModal.dismiss(true);
    });
  }
}
