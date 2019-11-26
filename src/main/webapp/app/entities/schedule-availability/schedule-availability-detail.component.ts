import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IScheduleAvailability } from 'app/shared/model/schedule-availability.model';

@Component({
  selector: 'jhi-schedule-availability-detail',
  templateUrl: './schedule-availability-detail.component.html'
})
export class ScheduleAvailabilityDetailComponent implements OnInit {
  scheduleAvailability: IScheduleAvailability;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ scheduleAvailability }) => {
      this.scheduleAvailability = scheduleAvailability;
    });
  }

  previousState() {
    window.history.back();
  }
}
