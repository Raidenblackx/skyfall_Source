import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IProyectActivity } from 'app/shared/model/proyect-activity.model';

@Component({
  selector: 'jhi-proyect-activity-detail',
  templateUrl: './proyect-activity-detail.component.html'
})
export class ProyectActivityDetailComponent implements OnInit {
  proyectActivity: IProyectActivity;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ proyectActivity }) => {
      this.proyectActivity = proyectActivity;
    });
  }

  previousState() {
    window.history.back();
  }
}
