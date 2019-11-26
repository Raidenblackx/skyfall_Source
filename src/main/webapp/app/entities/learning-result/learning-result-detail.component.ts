import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ILearningResult } from 'app/shared/model/learning-result.model';

@Component({
  selector: 'jhi-learning-result-detail',
  templateUrl: './learning-result-detail.component.html'
})
export class LearningResultDetailComponent implements OnInit {
  learningResult: ILearningResult;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ learningResult }) => {
      this.learningResult = learningResult;
    });
  }

  previousState() {
    window.history.back();
  }
}
