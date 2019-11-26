import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITypeEnvironment } from 'app/shared/model/type-environment.model';
import { TypeEnvironmentService } from './type-environment.service';

@Component({
  templateUrl: './type-environment-delete-dialog.component.html'
})
export class TypeEnvironmentDeleteDialogComponent {
  typeEnvironment: ITypeEnvironment;

  constructor(
    protected typeEnvironmentService: TypeEnvironmentService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.typeEnvironmentService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'typeEnvironmentListModification',
        content: 'Deleted an typeEnvironment'
      });
      this.activeModal.dismiss(true);
    });
  }
}
