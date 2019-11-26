import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IJourneyInstructor } from 'app/shared/model/journey-instructor.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { JourneyInstructorService } from './journey-instructor.service';
import { JourneyInstructorDeleteDialogComponent } from './journey-instructor-delete-dialog.component';

@Component({
  selector: 'jhi-journey-instructor',
  templateUrl: './journey-instructor.component.html'
})
export class JourneyInstructorComponent implements OnInit, OnDestroy {
  journeyInstructors: IJourneyInstructor[];
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
    protected journeyInstructorService: JourneyInstructorService,
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
    this.journeyInstructorService
      .query({
        page: this.page - 1,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe((res: HttpResponse<IJourneyInstructor[]>) => this.paginateJourneyInstructors(res.body, res.headers));
  }

  loadPage(page: number) {
    if (page !== this.previousPage) {
      this.previousPage = page;
      this.transition();
    }
  }

  transition() {
    this.router.navigate(['/journey-instructor'], {
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
      '/journey-instructor',
      {
        page: this.page,
        sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
      }
    ]);
    this.loadAll();
  }

  ngOnInit() {
    this.loadAll();
    this.registerChangeInJourneyInstructors();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IJourneyInstructor) {
    return item.id;
  }

  registerChangeInJourneyInstructors() {
    this.eventSubscriber = this.eventManager.subscribe('journeyInstructorListModification', () => this.loadAll());
  }

  delete(journeyInstructor: IJourneyInstructor) {
    const modalRef = this.modalService.open(JourneyInstructorDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.journeyInstructor = journeyInstructor;
  }

  sort() {
    const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateJourneyInstructors(data: IJourneyInstructor[], headers: HttpHeaders) {
    this.links = this.parseLinks.parse(headers.get('link'));
    this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
    this.journeyInstructors = data;
  }
}
