import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IInstructorArea } from 'app/shared/model/instructor-area.model';

@Component({
  selector: 'jhi-instructor-area-detail',
  templateUrl: './instructor-area-detail.component.html'
})
export class InstructorAreaDetailComponent implements OnInit {
  instructorArea: IInstructorArea;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ instructorArea }) => {
      this.instructorArea = instructorArea;
    });
  }

  previousState() {
    window.history.back();
  }
}
