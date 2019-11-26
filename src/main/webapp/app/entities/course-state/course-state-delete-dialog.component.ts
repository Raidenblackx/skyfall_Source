import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICourseState } from 'app/shared/model/course-state.model';
import { CourseStateService } from './course-state.service';

@Component({
  templateUrl: './course-state-delete-dialog.component.html'
})
export class CourseStateDeleteDialogComponent {
  courseState: ICourseState;

  constructor(
    protected courseStateService: CourseStateService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.courseStateService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'courseStateListModification',
        content: 'Deleted an courseState'
      });
      this.activeModal.dismiss(true);
    });
  }
}
