import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPlanningActivity } from 'app/shared/model/planning-activity.model';

@Component({
  selector: 'jhi-planning-activity-detail',
  templateUrl: './planning-activity-detail.component.html'
})
export class PlanningActivityDetailComponent implements OnInit {
  planningActivity: IPlanningActivity;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ planningActivity }) => {
      this.planningActivity = planningActivity;
    });
  }

  previousState() {
    window.history.back();
  }
}
