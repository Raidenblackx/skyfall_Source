import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITrimester } from 'app/shared/model/trimester.model';

@Component({
  selector: 'jhi-trimester-detail',
  templateUrl: './trimester-detail.component.html'
})
export class TrimesterDetailComponent implements OnInit {
  trimester: ITrimester;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ trimester }) => {
      this.trimester = trimester;
    });
  }

  previousState() {
    window.history.back();
  }
}
