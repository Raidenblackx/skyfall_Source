import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPhase } from 'app/shared/model/phase.model';

@Component({
  selector: 'jhi-phase-detail',
  templateUrl: './phase-detail.component.html'
})
export class PhaseDetailComponent implements OnInit {
  phase: IPhase;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ phase }) => {
      this.phase = phase;
    });
  }

  previousState() {
    window.history.back();
  }
}
