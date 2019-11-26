import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IProyect } from 'app/shared/model/proyect.model';

@Component({
  selector: 'jhi-proyect-detail',
  templateUrl: './proyect-detail.component.html'
})
export class ProyectDetailComponent implements OnInit {
  proyect: IProyect;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ proyect }) => {
      this.proyect = proyect;
    });
  }

  previousState() {
    window.history.back();
  }
}
