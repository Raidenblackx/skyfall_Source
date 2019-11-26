import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ILinkage } from 'app/shared/model/linkage.model';
import { LinkageService } from './linkage.service';

@Component({
  templateUrl: './linkage-delete-dialog.component.html'
})
export class LinkageDeleteDialogComponent {
  linkage: ILinkage;

  constructor(protected linkageService: LinkageService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.linkageService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'linkageListModification',
        content: 'Deleted an linkage'
      });
      this.activeModal.dismiss(true);
    });
  }
}
