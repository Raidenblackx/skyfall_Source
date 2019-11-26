import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IScheduleVersion } from 'app/shared/model/schedule-version.model';

@Component({
  selector: 'jhi-schedule-version-detail',
  templateUrl: './schedule-version-detail.component.html'
})
export class ScheduleVersionDetailComponent implements OnInit {
  scheduleVersion: IScheduleVersion;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ scheduleVersion }) => {
      this.scheduleVersion = scheduleVersion;
    });
  }

  previousState() {
    window.history.back();
  }
}
