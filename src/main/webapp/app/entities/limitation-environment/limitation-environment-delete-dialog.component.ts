import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ILimitationEnvironment } from 'app/shared/model/limitation-environment.model';
import { LimitationEnvironmentService } from './limitation-environment.service';

@Component({
  templateUrl: './limitation-environment-delete-dialog.component.html'
})
export class LimitationEnvironmentDeleteDialogComponent {
  limitationEnvironment: ILimitationEnvironment;

  constructor(
    protected limitationEnvironmentService: LimitationEnvironmentService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.limitationEnvironmentService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'limitationEnvironmentListModification',
        content: 'Deleted an limitationEnvironment'
      });
      this.activeModal.dismiss(true);
    });
  }
}
