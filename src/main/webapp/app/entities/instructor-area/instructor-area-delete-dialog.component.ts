import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IInstructorArea } from 'app/shared/model/instructor-area.model';
import { InstructorAreaService } from './instructor-area.service';

@Component({
  templateUrl: './instructor-area-delete-dialog.component.html'
})
export class InstructorAreaDeleteDialogComponent {
  instructorArea: IInstructorArea;

  constructor(
    protected instructorAreaService: InstructorAreaService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.instructorAreaService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'instructorAreaListModification',
        content: 'Deleted an instructorArea'
      });
      this.activeModal.dismiss(true);
    });
  }
}
