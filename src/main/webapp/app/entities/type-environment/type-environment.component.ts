import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITypeEnvironment } from 'app/shared/model/type-environment.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { TypeEnvironmentService } from './type-environment.service';
import { TypeEnvironmentDeleteDialogComponent } from './type-environment-delete-dialog.component';

@Component({
  selector: 'jhi-type-environment',
  templateUrl: './type-environment.component.html'
})
export class TypeEnvironmentComponent implements OnInit, OnDestroy {
  typeEnvironments: ITypeEnvironment[];
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
    protected typeEnvironmentService: TypeEnvironmentService,
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
    this.typeEnvironmentService
      .query({
        page: this.page - 1,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe((res: HttpResponse<ITypeEnvironment[]>) => this.paginateTypeEnvironments(res.body, res.headers));
  }

  loadPage(page: number) {
    if (page !== this.previousPage) {
      this.previousPage = page;
      this.transition();
    }
  }

  transition() {
    this.router.navigate(['/type-environment'], {
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
      '/type-environment',
      {
        page: this.page,
        sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
      }
    ]);
    this.loadAll();
  }

  ngOnInit() {
    this.loadAll();
    this.registerChangeInTypeEnvironments();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: ITypeEnvironment) {
    return item.id;
  }

  registerChangeInTypeEnvironments() {
    this.eventSubscriber = this.eventManager.subscribe('typeEnvironmentListModification', () => this.loadAll());
  }

  delete(typeEnvironment: ITypeEnvironment) {
    const modalRef = this.modalService.open(TypeEnvironmentDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.typeEnvironment = typeEnvironment;
  }

  sort() {
    const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateTypeEnvironments(data: ITypeEnvironment[], headers: HttpHeaders) {
    this.links = this.parseLinks.parse(headers.get('link'));
    this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
    this.typeEnvironments = data;
  }
}
