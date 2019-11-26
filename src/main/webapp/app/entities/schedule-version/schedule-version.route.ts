import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { ScheduleVersion } from 'app/shared/model/schedule-version.model';
import { ScheduleVersionService } from './schedule-version.service';
import { ScheduleVersionComponent } from './schedule-version.component';
import { ScheduleVersionDetailComponent } from './schedule-version-detail.component';
import { ScheduleVersionUpdateComponent } from './schedule-version-update.component';
import { IScheduleVersion } from 'app/shared/model/schedule-version.model';

@Injectable({ providedIn: 'root' })
export class ScheduleVersionResolve implements Resolve<IScheduleVersion> {
  constructor(private service: ScheduleVersionService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IScheduleVersion> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((scheduleVersion: HttpResponse<ScheduleVersion>) => scheduleVersion.body));
    }
    return of(new ScheduleVersion());
  }
}

export const scheduleVersionRoute: Routes = [
  {
    path: '',
    component: ScheduleVersionComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'skyfallApp.scheduleVersion.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ScheduleVersionDetailComponent,
    resolve: {
      scheduleVersion: ScheduleVersionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'skyfallApp.scheduleVersion.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ScheduleVersionUpdateComponent,
    resolve: {
      scheduleVersion: ScheduleVersionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'skyfallApp.scheduleVersion.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ScheduleVersionUpdateComponent,
    resolve: {
      scheduleVersion: ScheduleVersionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'skyfallApp.scheduleVersion.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
