import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITypeEnvironment } from 'app/shared/model/type-environment.model';

@Component({
  selector: 'jhi-type-environment-detail',
  templateUrl: './type-environment-detail.component.html'
})
export class TypeEnvironmentDetailComponent implements OnInit {
  typeEnvironment: ITypeEnvironment;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ typeEnvironment }) => {
      this.typeEnvironment = typeEnvironment;
    });
  }

  previousState() {
    window.history.back();
  }
}
