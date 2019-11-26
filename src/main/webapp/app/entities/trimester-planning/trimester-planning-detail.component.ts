import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITrimesterPlanning } from 'app/shared/model/trimester-planning.model';

@Component({
  selector: 'jhi-trimester-planning-detail',
  templateUrl: './trimester-planning-detail.component.html'
})
export class TrimesterPlanningDetailComponent implements OnInit {
  trimesterPlanning: ITrimesterPlanning;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ trimesterPlanning }) => {
      this.trimesterPlanning = trimesterPlanning;
    });
  }

  previousState() {
    window.history.back();
  }
}
