import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IResultSeen } from 'app/shared/model/result-seen.model';

@Component({
  selector: 'jhi-result-seen-detail',
  templateUrl: './result-seen-detail.component.html'
})
export class ResultSeenDetailComponent implements OnInit {
  resultSeen: IResultSeen;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ resultSeen }) => {
      this.resultSeen = resultSeen;
    });
  }

  previousState() {
    window.history.back();
  }
}
