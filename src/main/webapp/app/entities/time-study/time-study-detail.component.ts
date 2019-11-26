import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITimeStudy } from 'app/shared/model/time-study.model';

@Component({
  selector: 'jhi-time-study-detail',
  templateUrl: './time-study-detail.component.html'
})
export class TimeStudyDetailComponent implements OnInit {
  timeStudy: ITimeStudy;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ timeStudy }) => {
      this.timeStudy = timeStudy;
    });
  }

  previousState() {
    window.history.back();
  }
}
