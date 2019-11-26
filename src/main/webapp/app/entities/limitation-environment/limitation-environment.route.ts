import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { LimitationEnvironment } from 'app/shared/model/limitation-environment.model';
import { LimitationEnvironmentService } from './limitation-environment.service';
import { LimitationEnvironmentComponent } from './limitation-environment.component';
import { LimitationEnvironmentDetailComponent } from './limitation-environment-detail.component';
import { LimitationEnvironmentUpdateComponent } from './limitation-environment-update.component';
import { ILimitationEnvironment } from 'app/shared/model/limitation-environment.model';

@Injectable({ providedIn: 'root' })
export class LimitationEnvironmentResolve implements Resolve<ILimitationEnvironment> {
  constructor(private service: LimitationEnvironmentService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ILimitationEnvironment> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((limitationEnvironment: HttpResponse<LimitationEnvironment>) => limitationEnvironment.body));
    }
    return of(new LimitationEnvironment());
  }
}

export const limitationEnvironmentRoute: Routes = [
  {
    path: '',
    component: LimitationEnvironmentComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'skyfallApp.limitationEnvironment.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: LimitationEnvironmentDetailComponent,
    resolve: {
      limitationEnvironment: LimitationEnvironmentResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'skyfallApp.limitationEnvironment.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: LimitationEnvironmentUpdateComponent,
    resolve: {
      limitationEnvironment: LimitationEnvironmentResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'skyfallApp.limitationEnvironment.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: LimitationEnvironmentUpdateComponent,
    resolve: {
      limitationEnvironment: LimitationEnvironmentResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'skyfallApp.limitationEnvironment.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
