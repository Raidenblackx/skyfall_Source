import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISede } from 'app/shared/model/sede.model';
import { SedeService } from './sede.service';

@Component({
  templateUrl: './sede-delete-dialog.component.html'
})
export class SedeDeleteDialogComponent {
  sede: ISede;

  constructor(protected sedeService: SedeService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.sedeService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'sedeListModification',
        content: 'Deleted an sede'
      });
      this.activeModal.dismiss(true);
    });
  }
}
