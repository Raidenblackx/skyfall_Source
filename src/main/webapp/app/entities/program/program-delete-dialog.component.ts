import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IProgram } from 'app/shared/model/program.model';
import { ProgramService } from './program.service';

@Component({
  templateUrl: './program-delete-dialog.component.html'
})
export class ProgramDeleteDialogComponent {
  program: IProgram;

  constructor(protected programService: ProgramService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.programService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'programListModification',
        content: 'Deleted an program'
      });
      this.activeModal.dismiss(true);
    });
  }
}
