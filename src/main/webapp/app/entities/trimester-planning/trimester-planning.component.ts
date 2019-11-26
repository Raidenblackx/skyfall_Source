import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITrimesterPlanning } from 'app/shared/model/trimester-planning.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { TrimesterPlanningService } from './trimester-planning.service';
import { TrimesterPlanningDeleteDialogComponent } from './trimester-planning-delete-dialog.component';

@Component({
  selector: 'jhi-trimester-planning',
  templateUrl: './trimester-planning.component.html'
})
export class TrimesterPlanningComponent implements OnInit, OnDestroy {
  trimesterPlannings: ITrimesterPlanning[];
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
    protected trimesterPlanningService: TrimesterPlanningService,
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
    this.trimesterPlanningService
      .query({
        page: this.page - 1,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe((res: HttpResponse<ITrimesterPlanning[]>) => this.paginateTrimesterPlannings(res.body, res.headers));
  }

  loadPage(page: number) {
    if (page !== this.previousPage) {
      this.previousPage = page;
      this.transition();
    }
  }

  transition() {
    this.router.navigate(['/trimester-planning'], {
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
      '/trimester-planning',
      {
        page: this.page,
        sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
      }
    ]);
    this.loadAll();
  }

  ngOnInit() {
    this.loadAll();
    this.registerChangeInTrimesterPlannings();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: ITrimesterPlanning) {
    return item.id;
  }

  registerChangeInTrimesterPlannings() {
    this.eventSubscriber = this.eventManager.subscribe('trimesterPlanningListModification', () => this.loadAll());
  }

  delete(trimesterPlanning: ITrimesterPlanning) {
    const modalRef = this.modalService.open(TrimesterPlanningDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.trimesterPlanning = trimesterPlanning;
  }

  sort() {
    const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateTrimesterPlannings(data: ITrimesterPlanning[], headers: HttpHeaders) {
    this.links = this.parseLinks.parse(headers.get('link'));
    this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
    this.trimesterPlannings = data;
  }
}
