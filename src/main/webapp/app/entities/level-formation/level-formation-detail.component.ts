import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ILevelFormation } from 'app/shared/model/level-formation.model';

@Component({
  selector: 'jhi-level-formation-detail',
  templateUrl: './level-formation-detail.component.html'
})
export class LevelFormationDetailComponent implements OnInit {
  levelFormation: ILevelFormation;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ levelFormation }) => {
      this.levelFormation = levelFormation;
    });
  }

  previousState() {
    window.history.back();
  }
}
