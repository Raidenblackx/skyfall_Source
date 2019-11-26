import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ILearningResult } from 'app/shared/model/learning-result.model';
import { LearningResultService } from './learning-result.service';

@Component({
  templateUrl: './learning-result-delete-dialog.component.html'
})
export class LearningResultDeleteDialogComponent {
  learningResult: ILearningResult;

  constructor(
    protected learningResultService: LearningResultService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.learningResultService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'learningResultListModification',
        content: 'Deleted an learningResult'
      });
      this.activeModal.dismiss(true);
    });
  }
}
