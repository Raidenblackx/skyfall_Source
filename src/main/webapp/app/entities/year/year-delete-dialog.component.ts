import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IYear } from 'app/shared/model/year.model';
import { YearService } from './year.service';

@Component({
  templateUrl: './year-delete-dialog.component.html'
})
export class YearDeleteDialogComponent {
  year: IYear;

  constructor(protected yearService: YearService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.yearService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'yearListModification',
        content: 'Deleted an year'
      });
      this.activeModal.dismiss(true);
    });
  }
}
