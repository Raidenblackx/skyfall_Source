import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICurrentQuarter } from 'app/shared/model/current-quarter.model';

@Component({
  selector: 'jhi-current-quarter-detail',
  templateUrl: './current-quarter-detail.component.html'
})
export class CurrentQuarterDetailComponent implements OnInit {
  currentQuarter: ICurrentQuarter;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ currentQuarter }) => {
      this.currentQuarter = currentQuarter;
    });
  }

  previousState() {
    window.history.back();
  }
}
