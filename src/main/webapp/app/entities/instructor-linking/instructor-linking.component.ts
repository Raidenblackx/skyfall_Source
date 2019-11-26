import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IInstructorLinking } from 'app/shared/model/instructor-linking.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { InstructorLinkingService } from './instructor-linking.service';
import { InstructorLinkingDeleteDialogComponent } from './instructor-linking-delete-dialog.component';

@Component({
  selector: 'jhi-instructor-linking',
  templateUrl: './instructor-linking.component.html'
})
export class InstructorLinkingComponent implements OnInit, OnDestroy {
  instructorLinkings: IInstructorLinking[];
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
    protected instructorLinkingService: InstructorLinkingService,
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
    this.instructorLinkingService
      .query({
        page: this.page - 1,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe((res: HttpResponse<IInstructorLinking[]>) => this.paginateInstructorLinkings(res.body, res.headers));
  }

  loadPage(page: number) {
    if (page !== this.previousPage) {
      this.previousPage = page;
      this.transition();
    }
  }

  transition() {
    this.router.navigate(['/instructor-linking'], {
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
      '/instructor-linking',
      {
        page: this.page,
        sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
      }
    ]);
    this.loadAll();
  }

  ngOnInit() {
    this.loadAll();
    this.registerChangeInInstructorLinkings();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IInstructorLinking) {
    return item.id;
  }

  registerChangeInInstructorLinkings() {
    this.eventSubscriber = this.eventManager.subscribe('instructorLinkingListModification', () => this.loadAll());
  }

  delete(instructorLinking: IInstructorLinking) {
    const modalRef = this.modalService.open(InstructorLinkingDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.instructorLinking = instructorLinking;
  }

  sort() {
    const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateInstructorLinkings(data: IInstructorLinking[], headers: HttpHeaders) {
    this.links = this.parseLinks.parse(headers.get('link'));
    this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
    this.instructorLinkings = data;
  }
}
