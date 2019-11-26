import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IScheduleVersion } from 'app/shared/model/schedule-version.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { ScheduleVersionService } from './schedule-version.service';
import { ScheduleVersionDeleteDialogComponent } from './schedule-version-delete-dialog.component';

@Component({
  selector: 'jhi-schedule-version',
  templateUrl: './schedule-version.component.html'
})
export class ScheduleVersionComponent implements OnInit, OnDestroy {
  scheduleVersions: IScheduleVersion[];
  error: any;
  success: any;
  eventSubscriber: Subscription;
  routeData: any;
  links: any;
  totalItems: any;
  itemsPerPage: any;
  page: any;
  predicate: any;
  previousPage: any;
  reverse: any;

  constructor(
    protected scheduleVersionService: ScheduleVersionService,
    protected parseLinks: JhiParseLinks,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.routeData = this.activatedRoute.data.subscribe(data => {
      this.page = data.pagingParams.page;
      this.previousPage = data.pagingParams.page;
      this.reverse = data.pagingParams.ascending;
      this.predicate = data.pagingParams.predicate;
    });
  }

  loadAll() {
    this.scheduleVersionService
      .query({
        page: this.page - 1,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe((res: HttpResponse<IScheduleVersion[]>) => this.paginateScheduleVersions(res.body, res.headers));
  }

  loadPage(page: number) {
    if (page !== this.previousPage) {
      this.previousPage = page;
      this.transition();
    }
  }

  transition() {
    this.router.navigate(['/schedule-version'], {
      queryParams: {
        page: this.page,
        size: this.itemsPerPage,
        sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
      }
    });
    this.loadAll();
  }

  clear() {
    this.page = 0;
    this.router.navigate([
      '/schedule-version',
      {
        page: this.page,
        sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
      }
    ]);
    this.loadAll();
  }

  ngOnInit() {
    this.loadAll();
    this.registerChangeInScheduleVersions();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IScheduleVersion) {
    return item.id;
  }

  registerChangeInScheduleVersions() {
    this.eventSubscriber = this.eventManager.subscribe('scheduleVersionListModification', () => this.loadAll());
  }

  delete(scheduleVersion: IScheduleVersion) {
    const modalRef = this.modalService.open(ScheduleVersionDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.scheduleVersion = scheduleVersion;
  }

  sort() {
    const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateScheduleVersions(data: IScheduleVersion[], headers: HttpHeaders) {
    this.links = this.parseLinks.parse(headers.get('link'));
    this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
    this.scheduleVersions = data;
  }
}
