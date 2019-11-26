import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { CurrentQuarter } from 'app/shared/model/current-quarter.model';
import { CurrentQuarterService } from './current-quarter.service';
import { CurrentQuarterComponent } from './current-quarter.component';
import { CurrentQuarterDetailComponent } from './current-quarter-detail.component';
import { CurrentQuarterUpdateComponent } from './current-quarter-update.component';
import { ICurrentQuarter } from 'app/shared/model/current-quarter.model';

@Injectable({ providedIn: 'root' })
export class CurrentQuarterResolve implements Resolve<ICurrentQuarter> {
  constructor(private service: CurrentQuarterService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICurrentQuarter> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((currentQuarter: HttpResponse<CurrentQuarter>) => currentQuarter.body));
    }
    return of(new CurrentQuarter());
  }
}

export const currentQuarterRoute: Routes = [
  {
    path: '',
    component: CurrentQuarterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'skyfallApp.currentQuarter.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: CurrentQuarterDetailComponent,
    resolve: {
      currentQuarter: CurrentQuarterResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'skyfallApp.currentQuarter.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: CurrentQuarterUpdateComponent,
    resolve: {
      currentQuarter: CurrentQuarterResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'skyfallApp.currentQuarter.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: CurrentQuarterUpdateComponent,
    resolve: {
      currentQuarter: CurrentQuarterResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'skyfallApp.currentQuarter.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
