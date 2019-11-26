import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IModality } from 'app/shared/model/modality.model';

@Component({
  selector: 'jhi-modality-detail',
  templateUrl: './modality-detail.component.html'
})
export class ModalityDetailComponent implements OnInit {
  modality: IModality;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ modality }) => {
      this.modality = modality;
    });
  }

  previousState() {
    window.history.back();
  }
}
