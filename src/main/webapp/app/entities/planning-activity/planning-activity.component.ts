import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IPlanningActivity } from 'app/shared/model/planning-activity.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { PlanningActivityService } from './planning-activity.service';
import { PlanningActivityDeleteDialogComponent } from './planning-activity-delete-dialog.component';

@Component({
  selector: 'jhi-planning-activity',
  templateUrl: './planning-activity.component.html'
})
export class PlanningActivityComponent implements OnInit, OnDestroy {
  planningActivities: IPlanningActivity[];
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
    protected planningActivityService: PlanningActivityService,
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
    this.planningActivityService
      .query({
        page: this.page - 1,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe((res: HttpResponse<IPlanningActivity[]>) => this.paginatePlanningActivities(res.body, res.headers));
  }

  loadPage(page: number) {
    if (page !== this.previousPage) {
      this.previousPage = page;
      this.transition();
    }
  }

  transition() {
    this.router.navigate(['/planning-activity'], {
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
      '/planning-activity',
      {
        page: this.page,
        sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
      }
    ]);
    this.loadAll();
  }

  ngOnInit() {
    this.loadAll();
    this.registerChangeInPlanningActivities();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IPlanningActivity) {
    return item.id;
  }

  registerChangeInPlanningActivities() {
    this.eventSubscriber = this.eventManager.subscribe('planningActivityListModification', () => this.loadAll());
  }

  delete(planningActivity: IPlanningActivity) {
    const modalRef = this.modalService.open(PlanningActivityDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.planningActivity = planningActivity;
  }

  sort() {
    const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginatePlanningActivities(data: IPlanningActivity[], headers: HttpHeaders) {
    this.links = this.parseLinks.parse(headers.get('link'));
    this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
    this.planningActivities = data;
  }
}
