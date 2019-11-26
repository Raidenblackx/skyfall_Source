import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IProyectActivity } from 'app/shared/model/proyect-activity.model';
import { ProyectActivityService } from './proyect-activity.service';

@Component({
  templateUrl: './proyect-activity-delete-dialog.component.html'
})
export class ProyectActivityDeleteDialogComponent {
  proyectActivity: IProyectActivity;

  constructor(
    protected proyectActivityService: ProyectActivityService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.proyectActivityService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'proyectActivityListModification',
        content: 'Deleted an proyectActivity'
      });
      this.activeModal.dismiss(true);
    });
  }
}
