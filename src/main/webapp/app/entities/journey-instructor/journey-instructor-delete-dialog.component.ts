import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IJourneyInstructor } from 'app/shared/model/journey-instructor.model';
import { JourneyInstructorService } from './journey-instructor.service';

@Component({
  templateUrl: './journey-instructor-delete-dialog.component.html'
})
export class JourneyInstructorDeleteDialogComponent {
  journeyInstructor: IJourneyInstructor;

  constructor(
    protected journeyInstructorService: JourneyInstructorService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.journeyInstructorService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'journeyInstructorListModification',
        content: 'Deleted an journeyInstructor'
      });
      this.activeModal.dismiss(true);
    });
  }
}
