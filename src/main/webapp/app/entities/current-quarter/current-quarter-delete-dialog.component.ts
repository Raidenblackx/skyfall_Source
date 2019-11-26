import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICurrentQuarter } from 'app/shared/model/current-quarter.model';
import { CurrentQuarterService } from './current-quarter.service';

@Component({
  templateUrl: './current-quarter-delete-dialog.component.html'
})
export class CurrentQuarterDeleteDialogComponent {
  currentQuarter: ICurrentQuarter;

  constructor(
    protected currentQuarterService: CurrentQuarterService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.currentQuarterService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'currentQuarterListModification',
        content: 'Deleted an currentQuarter'
      });
      this.activeModal.dismiss(true);
    });
  }
}
