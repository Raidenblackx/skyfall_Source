import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IInstructorLinking } from 'app/shared/model/instructor-linking.model';
import { InstructorLinkingService } from './instructor-linking.service';

@Component({
  templateUrl: './instructor-linking-delete-dialog.component.html'
})
export class InstructorLinkingDeleteDialogComponent {
  instructorLinking: IInstructorLinking;

  constructor(
    protected instructorLinkingService: InstructorLinkingService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.instructorLinkingService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'instructorLinkingListModification',
        content: 'Deleted an instructorLinking'
      });
      this.activeModal.dismiss(true);
    });
  }
}
