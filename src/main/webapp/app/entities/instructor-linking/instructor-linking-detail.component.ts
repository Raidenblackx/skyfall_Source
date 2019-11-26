import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IInstructorLinking } from 'app/shared/model/instructor-linking.model';

@Component({
  selector: 'jhi-instructor-linking-detail',
  templateUrl: './instructor-linking-detail.component.html'
})
export class InstructorLinkingDetailComponent implements OnInit {
  instructorLinking: IInstructorLinking;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ instructorLinking }) => {
      this.instructorLinking = instructorLinking;
    });
  }

  previousState() {
    window.history.back();
  }
}
