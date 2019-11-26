import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICourseHasTrimester } from 'app/shared/model/course-has-trimester.model';

@Component({
  selector: 'jhi-course-has-trimester-detail',
  templateUrl: './course-has-trimester-detail.component.html'
})
export class CourseHasTrimesterDetailComponent implements OnInit {
  courseHasTrimester: ICourseHasTrimester;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ courseHasTrimester }) => {
      this.courseHasTrimester = courseHasTrimester;
    });
  }

  previousState() {
    window.history.back();
  }
}
