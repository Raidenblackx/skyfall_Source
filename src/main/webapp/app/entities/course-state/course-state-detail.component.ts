import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICourseState } from 'app/shared/model/course-state.model';

@Component({
  selector: 'jhi-course-state-detail',
  templateUrl: './course-state-detail.component.html'
})
export class CourseStateDetailComponent implements OnInit {
  courseState: ICourseState;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ courseState }) => {
      this.courseState = courseState;
    });
  }

  previousState() {
    window.history.back();
  }
}
