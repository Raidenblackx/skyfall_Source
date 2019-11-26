import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAvailabilityCompetition } from 'app/shared/model/availability-competition.model';

@Component({
  selector: 'jhi-availability-competition-detail',
  templateUrl: './availability-competition-detail.component.html'
})
export class AvailabilityCompetitionDetailComponent implements OnInit {
  availabilityCompetition: IAvailabilityCompetition;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ availabilityCompetition }) => {
      this.availabilityCompetition = availabilityCompetition;
    });
  }

  previousState() {
    window.history.back();
  }
}
