import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IWorkingDay } from 'app/shared/model/working-day.model';

@Component({
  selector: 'jhi-working-day-detail',
  templateUrl: './working-day-detail.component.html'
})
export class WorkingDayDetailComponent implements OnInit {
  workingDay: IWorkingDay;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ workingDay }) => {
      this.workingDay = workingDay;
    });
  }

  byteSize(field) {
    return this.dataUtils.byteSize(field);
  }

  openFile(contentType, field) {
    return this.dataUtils.openFile(contentType, field);
  }
  previousState() {
    window.history.back();
  }
}
