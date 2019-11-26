import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IScheduleAvailability } from 'app/shared/model/schedule-availability.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { ScheduleAvailabilityService } from './schedule-availability.service';
import { ScheduleAvailabilityDeleteDialogComponent } from './schedule-availability-delete-dialog.component';

@Component({
  selector: 'jhi-schedule-availability',
  templateUrl: './schedule-availability.component.html'
})
export class ScheduleAvailabilityComponent implements OnInit, OnDestroy {
  scheduleAvailabilities: IScheduleAvailability[];
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
    protected scheduleAvailabilityService: ScheduleAvailabilityService,
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
    this.scheduleAvailabilityService
      .query({
        page: this.page - 1,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe((res: HttpResponse<IScheduleAvailability[]>) => this.paginateScheduleAvailabilities(res.body, res.headers));
  }

  loadPage(page: number) {
    if (page !== this.previousPage) {
      this.previousPage = page;
      this.transition();
    }
  }

  transition() {
    this.router.navigate(['/schedule-availability'], {
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
      '/schedule-availability',
      {
        page: this.page,
        sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
      }
    ]);
    this.loadAll();
  }

  ngOnInit() {
    this.loadAll();
    this.registerChangeInScheduleAvailabilities();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IScheduleAvailability) {
    return item.id;
  }

  registerChangeInScheduleAvailabilities() {
    this.eventSubscriber = this.eventManager.subscribe('scheduleAvailabilityListModification', () => this.loadAll());
  }

  delete(scheduleAvailability: IScheduleAvailability) {
    const modalRef = this.modalService.open(ScheduleAvailabilityDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.scheduleAvailability = scheduleAvailability;
  }

  sort() {
    const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateScheduleAvailabilities(data: IScheduleAvailability[], headers: HttpHeaders) {
    this.links = this.parseLinks.parse(headers.get('link'));
    this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
    this.scheduleAvailabilities = data;
  }
}
