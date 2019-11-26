import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { TrimesterPlanning } from 'app/shared/model/trimester-planning.model';
import { TrimesterPlanningService } from './trimester-planning.service';
import { TrimesterPlanningComponent } from './trimester-planning.component';
import { TrimesterPlanningDetailComponent } from './trimester-planning-detail.component';
import { TrimesterPlanningUpdateComponent } from './trimester-planning-update.component';
import { ITrimesterPlanning } from 'app/shared/model/trimester-planning.model';

@Injectable({ providedIn: 'root' })
export class TrimesterPlanningResolve implements Resolve<ITrimesterPlanning> {
  constructor(private service: TrimesterPlanningService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITrimesterPlanning> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((trimesterPlanning: HttpResponse<TrimesterPlanning>) => trimesterPlanning.body));
    }
    return of(new TrimesterPlanning());
  }
}

export const trimesterPlanningRoute: Routes = [
  {
    path: '',
    component: TrimesterPlanningComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'skyfallApp.trimesterPlanning.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: TrimesterPlanningDetailComponent,
    resolve: {
      trimesterPlanning: TrimesterPlanningResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'skyfallApp.trimesterPlanning.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: TrimesterPlanningUpdateComponent,
    resolve: {
      trimesterPlanning: TrimesterPlanningResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'skyfallApp.trimesterPlanning.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: TrimesterPlanningUpdateComponent,
    resolve: {
      trimesterPlanning: TrimesterPlanningResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'skyfallApp.trimesterPlanning.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
