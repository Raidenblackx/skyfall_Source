import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ILimitationEnvironment } from 'app/shared/model/limitation-environment.model';

@Component({
  selector: 'jhi-limitation-environment-detail',
  templateUrl: './limitation-environment-detail.component.html'
})
export class LimitationEnvironmentDetailComponent implements OnInit {
  limitationEnvironment: ILimitationEnvironment;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ limitationEnvironment }) => {
      this.limitationEnvironment = limitationEnvironment;
    });
  }

  previousState() {
    window.history.back();
  }
}
