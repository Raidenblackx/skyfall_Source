import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { PlanningActivity } from 'app/shared/model/planning-activity.model';
import { PlanningActivityService } from './planning-activity.service';
import { PlanningActivityComponent } from './planning-activity.component';
import { PlanningActivityDetailComponent } from './planning-activity-detail.component';
import { PlanningActivityUpdateComponent } from './planning-activity-update.component';
import { IPlanningActivity } from 'app/shared/model/planning-activity.model';

@Injectable({ providedIn: 'root' })
export class PlanningActivityResolve implements Resolve<IPlanningActivity> {
  constructor(private service: PlanningActivityService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IPlanningActivity> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((planningActivity: HttpResponse<PlanningActivity>) => planningActivity.body));
    }
    return of(new PlanningActivity());
  }
}

export const planningActivityRoute: Routes = [
  {
    path: '',
    component: PlanningActivityComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'skyfallApp.planningActivity.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: PlanningActivityDetailComponent,
    resolve: {
      planningActivity: PlanningActivityResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'skyfallApp.planningActivity.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: PlanningActivityUpdateComponent,
    resolve: {
      planningActivity: PlanningActivityResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'skyfallApp.planningActivity.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: PlanningActivityUpdateComponent,
    resolve: {
      planningActivity: PlanningActivityResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'skyfallApp.planningActivity.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
