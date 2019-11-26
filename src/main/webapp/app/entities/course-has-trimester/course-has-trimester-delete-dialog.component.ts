import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICourseHasTrimester } from 'app/shared/model/course-has-trimester.model';
import { CourseHasTrimesterService } from './course-has-trimester.service';

@Component({
  templateUrl: './course-has-trimester-delete-dialog.component.html'
})
export class CourseHasTrimesterDeleteDialogComponent {
  courseHasTrimester: ICourseHasTrimester;

  constructor(
    protected courseHasTrimesterService: CourseHasTrimesterService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.courseHasTrimesterService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'courseHasTrimesterListModification',
        content: 'Deleted an courseHasTrimester'
      });
      this.activeModal.dismiss(true);
    });
  }
}
