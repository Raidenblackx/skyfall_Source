import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IAvailabilityCompetition } from 'app/shared/model/availability-competition.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { AvailabilityCompetitionService } from './availability-competition.service';
import { AvailabilityCompetitionDeleteDialogComponent } from './availability-competition-delete-dialog.component';

@Component({
  selector: 'jhi-availability-competition',
  templateUrl: './availability-competition.component.html'
})
export class AvailabilityCompetitionComponent implements OnInit, OnDestroy {
  availabilityCompetitions: IAvailabilityCompetition[];
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
    protected availabilityCompetitionService: AvailabilityCompetitionService,
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
    this.availabilityCompetitionService
      .query({
        page: this.page - 1,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe((res: HttpResponse<IAvailabilityCompetition[]>) => this.paginateAvailabilityCompetitions(res.body, res.headers));
  }

  loadPage(page: number) {
    if (page !== this.previousPage) {
      this.previousPage = page;
      this.transition();
    }
  }

  transition() {
    this.router.navigate(['/availability-competition'], {
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
      '/availability-competition',
      {
        page: this.page,
        sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
      }
    ]);
    this.loadAll();
  }

  ngOnInit() {
    this.loadAll();
    this.registerChangeInAvailabilityCompetitions();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IAvailabilityCompetition) {
    return item.id;
  }

  registerChangeInAvailabilityCompetitions() {
    this.eventSubscriber = this.eventManager.subscribe('availabilityCompetitionListModification', () => this.loadAll());
  }

  delete(availabilityCompetition: IAvailabilityCompetition) {
    const modalRef = this.modalService.open(AvailabilityCompetitionDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.availabilityCompetition = availabilityCompetition;
  }

  sort() {
    const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateAvailabilityCompetitions(data: IAvailabilityCompetition[], headers: HttpHeaders) {
    this.links = this.parseLinks.parse(headers.get('link'));
    this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
    this.availabilityCompetitions = data;
  }
}
