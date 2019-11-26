import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { ResultSeen } from 'app/shared/model/result-seen.model';
import { ResultSeenService } from './result-seen.service';
import { ResultSeenComponent } from './result-seen.component';
import { ResultSeenDetailComponent } from './result-seen-detail.component';
import { ResultSeenUpdateComponent } from './result-seen-update.component';
import { IResultSeen } from 'app/shared/model/result-seen.model';

@Injectable({ providedIn: 'root' })
export class ResultSeenResolve implements Resolve<IResultSeen> {
  constructor(private service: ResultSeenService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IResultSeen> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((resultSeen: HttpResponse<ResultSeen>) => resultSeen.body));
    }
    return of(new ResultSeen());
  }
}

export const resultSeenRoute: Routes = [
  {
    path: '',
    component: ResultSeenComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'skyfallApp.resultSeen.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ResultSeenDetailComponent,
    resolve: {
      resultSeen: ResultSeenResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'skyfallApp.resultSeen.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ResultSeenUpdateComponent,
    resolve: {
      resultSeen: ResultSeenResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'skyfallApp.resultSeen.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ResultSeenUpdateComponent,
    resolve: {
      resultSeen: ResultSeenResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'skyfallApp.resultSeen.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
