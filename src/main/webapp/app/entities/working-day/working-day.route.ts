import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { WorkingDay } from 'app/shared/model/working-day.model';
import { WorkingDayService } from './working-day.service';
import { WorkingDayComponent } from './working-day.component';
import { WorkingDayDetailComponent } from './working-day-detail.component';
import { WorkingDayUpdateComponent } from './working-day-update.component';
import { IWorkingDay } from 'app/shared/model/working-day.model';

@Injectable({ providedIn: 'root' })
export class WorkingDayResolve implements Resolve<IWorkingDay> {
  constructor(private service: WorkingDayService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IWorkingDay> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((workingDay: HttpResponse<WorkingDay>) => workingDay.body));
    }
    return of(new WorkingDay());
  }
}

export const workingDayRoute: Routes = [
  {
    path: '',
    component: WorkingDayComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'skyfallApp.workingDay.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: WorkingDayDetailComponent,
    resolve: {
      workingDay: WorkingDayResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'skyfallApp.workingDay.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: WorkingDayUpdateComponent,
    resolve: {
      workingDay: WorkingDayResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'skyfallApp.workingDay.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: WorkingDayUpdateComponent,
    resolve: {
      workingDay: WorkingDayResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'skyfallApp.workingDay.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
