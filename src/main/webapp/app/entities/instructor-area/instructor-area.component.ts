import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IInstructorArea } from 'app/shared/model/instructor-area.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { InstructorAreaService } from './instructor-area.service';
import { InstructorAreaDeleteDialogComponent } from './instructor-area-delete-dialog.component';

@Component({
  selector: 'jhi-instructor-area',
  templateUrl: './instructor-area.component.html'
})
export class InstructorAreaComponent implements OnInit, OnDestroy {
  instructorAreas: IInstructorArea[];
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
    protected instructorAreaService: InstructorAreaService,
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
    this.instructorAreaService
      .query({
        page: this.page - 1,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe((res: HttpResponse<IInstructorArea[]>) => this.paginateInstructorAreas(res.body, res.headers));
  }

  loadPage(page: number) {
    if (page !== this.previousPage) {
      this.previousPage = page;
      this.transition();
    }
  }

  transition() {
    this.router.navigate(['/instructor-area'], {
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
      '/instructor-area',
      {
        page: this.page,
        sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
      }
    ]);
    this.loadAll();
  }

  ngOnInit() {
    this.loadAll();
    this.registerChangeInInstructorAreas();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IInstructorArea) {
    return item.id;
  }

  registerChangeInInstructorAreas() {
    this.eventSubscriber = this.eventManager.subscribe('instructorAreaListModification', () => this.loadAll());
  }

  delete(instructorArea: IInstructorArea) {
    const modalRef = this.modalService.open(InstructorAreaDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.instructorArea = instructorArea;
  }

  sort() {
    const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateInstructorAreas(data: IInstructorArea[], headers: HttpHeaders) {
    this.links = this.parseLinks.parse(headers.get('link'));
    this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
    this.instructorAreas = data;
  }
}
