import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAmbient } from 'app/shared/model/ambient.model';

@Component({
  selector: 'jhi-ambient-detail',
  templateUrl: './ambient-detail.component.html'
})
export class AmbientDetailComponent implements OnInit {
  ambient: IAmbient;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ ambient }) => {
      this.ambient = ambient;
    });
  }

  previousState() {
    window.history.back();
  }
}
