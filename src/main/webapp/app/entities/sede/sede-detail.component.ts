import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISede } from 'app/shared/model/sede.model';

@Component({
  selector: 'jhi-sede-detail',
  templateUrl: './sede-detail.component.html'
})
export class SedeDetailComponent implements OnInit {
  sede: ISede;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ sede }) => {
      this.sede = sede;
    });
  }

  previousState() {
    window.history.back();
  }
}
