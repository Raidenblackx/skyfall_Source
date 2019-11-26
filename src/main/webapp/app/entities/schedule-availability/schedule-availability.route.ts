import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { ScheduleAvailability } from 'app/shared/model/schedule-availability.model';
import { ScheduleAvailabilityService } from './schedule-availability.service';
import { ScheduleAvailabilityComponent } from './schedule-availability.component';
import { ScheduleAvailabilityDetailComponent } from './schedule-availability-detail.component';
import { ScheduleAvailabilityUpdateComponent } from './schedule-availability-update.component';
import { IScheduleAvailability } from 'app/shared/model/schedule-availability.model';

@Injectable({ providedIn: 'root' })
export class ScheduleAvailabilityResolve implements Resolve<IScheduleAvailability> {
  constructor(private service: ScheduleAvailabilityService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IScheduleAvailability> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((scheduleAvailability: HttpResponse<ScheduleAvailability>) => scheduleAvailability.body));
    }
    return of(new ScheduleAvailability());
  }
}

export const scheduleAvailabilityRoute: Routes = [
  {
    path: '',
    component: ScheduleAvailabilityComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'skyfallApp.scheduleAvailability.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ScheduleAvailabilityDetailComponent,
    resolve: {
      scheduleAvailability: ScheduleAvailabilityResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'skyfallApp.scheduleAvailability.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ScheduleAvailabilityUpdateComponent,
    resolve: {
      scheduleAvailability: ScheduleAvailabilityResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'skyfallApp.scheduleAvailability.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ScheduleAvailabilityUpdateComponent,
    resolve: {
      scheduleAvailability: ScheduleAvailabilityResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'skyfallApp.scheduleAvailability.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
