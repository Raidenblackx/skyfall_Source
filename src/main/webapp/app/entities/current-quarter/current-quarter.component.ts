import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ICurrentQuarter } from 'app/shared/model/current-quarter.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { CurrentQuarterService } from './current-quarter.service';
import { CurrentQuarterDeleteDialogComponent } from './current-quarter-delete-dialog.component';

@Component({
  selector: 'jhi-current-quarter',
  templateUrl: './current-quarter.component.html'
})
export class CurrentQuarterComponent implements OnInit, OnDestroy {
  currentQuarters: ICurrentQuarter[];
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
    protected currentQuarterService: CurrentQuarterService,
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
    this.currentQuarterService
      .query({
        page: this.page - 1,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe((res: HttpResponse<ICurrentQuarter[]>) => this.paginateCurrentQuarters(res.body, res.headers));
  }

  loadPage(page: number) {
    if (page !== this.previousPage) {
      this.previousPage = page;
      this.transition();
    }
  }

  transition() {
    this.router.navigate(['/current-quarter'], {
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
      '/current-quarter',
      {
        page: this.page,
        sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
      }
    ]);
    this.loadAll();
  }

  ngOnInit() {
    this.loadAll();
    this.registerChangeInCurrentQuarters();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: ICurrentQuarter) {
    return item.id;
  }

  registerChangeInCurrentQuarters() {
    this.eventSubscriber = this.eventManager.subscribe('currentQuarterListModification', () => this.loadAll());
  }

  delete(currentQuarter: ICurrentQuarter) {
    const modalRef = this.modalService.open(CurrentQuarterDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.currentQuarter = currentQuarter;
  }

  sort() {
    const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateCurrentQuarters(data: ICurrentQuarter[], headers: HttpHeaders) {
    this.links = this.parseLinks.parse(headers.get('link'));
    this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
    this.currentQuarters = data;
  }
}
