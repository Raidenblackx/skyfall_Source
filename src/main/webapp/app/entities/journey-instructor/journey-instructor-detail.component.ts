import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IJourneyInstructor } from 'app/shared/model/journey-instructor.model';

@Component({
  selector: 'jhi-journey-instructor-detail',
  templateUrl: './journey-instructor-detail.component.html'
})
export class JourneyInstructorDetailComponent implements OnInit {
  journeyInstructor: IJourneyInstructor;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ journeyInstructor }) => {
      this.journeyInstructor = journeyInstructor;
    });
  }

  previousState() {
    window.history.back();
  }
}
