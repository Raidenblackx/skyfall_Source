import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ILinkage } from 'app/shared/model/linkage.model';

@Component({
  selector: 'jhi-linkage-detail',
  templateUrl: './linkage-detail.component.html'
})
export class LinkageDetailComponent implements OnInit {
  linkage: ILinkage;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ linkage }) => {
      this.linkage = linkage;
    });
  }

  previousState() {
    window.history.back();
  }
}
