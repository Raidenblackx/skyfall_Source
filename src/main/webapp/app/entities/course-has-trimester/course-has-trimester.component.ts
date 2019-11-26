import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ICourseHasTrimester } from 'app/shared/model/course-has-trimester.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { CourseHasTrimesterService } from './course-has-trimester.service';
import { CourseHasTrimesterDeleteDialogComponent } from './course-has-trimester-delete-dialog.component';

@Component({
  selector: 'jhi-course-has-trimester',
  templateUrl: './course-has-trimester.component.html'
})
export class CourseHasTrimesterComponent implements OnInit, OnDestroy {
  courseHasTrimesters: ICourseHasTrimester[];
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
    protected courseHasTrimesterService: CourseHasTrimesterService,
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
    this.courseHasTrimesterService
      .query({
        page: this.page - 1,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe((res: HttpResponse<ICourseHasTrimester[]>) => this.paginateCourseHasTrimesters(res.body, res.headers));
  }

  loadPage(page: number) {
    if (page !== this.previousPage) {
      this.previousPage = page;
      this.transition();
    }
  }

  transition() {
    this.router.navigate(['/course-has-trimester'], {
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
      '/course-has-trimester',
      {
        page: this.page,
        sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
      }
    ]);
    this.loadAll();
  }

  ngOnInit() {
    this.loadAll();
    this.registerChangeInCourseHasTrimesters();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: ICourseHasTrimester) {
    return item.id;
  }

  registerChangeInCourseHasTrimesters() {
    this.eventSubscriber = this.eventManager.subscribe('courseHasTrimesterListModification', () => this.loadAll());
  }

  delete(courseHasTrimester: ICourseHasTrimester) {
    const modalRef = this.modalService.open(CourseHasTrimesterDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.courseHasTrimester = courseHasTrimester;
  }

  sort() {
    const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateCourseHasTrimesters(data: ICourseHasTrimester[], headers: HttpHeaders) {
    this.links = this.parseLinks.parse(headers.get('link'));
    this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
    this.courseHasTrimesters = data;
  }
}
