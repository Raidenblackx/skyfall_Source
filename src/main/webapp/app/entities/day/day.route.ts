import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Day } from 'app/shared/model/day.model';
import { DayService } from './day.service';
import { DayComponent } from './day.component';
import { DayDetailComponent } from './day-detail.component';
import { DayUpdateComponent } from './day-update.component';
import { IDay } from 'app/shared/model/day.model';

@Injectable({ providedIn: 'root' })
export class DayResolve implements Resolve<IDay> {
  constructor(private service: DayService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDay> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((day: HttpResponse<Day>) => day.body));
    }
    return of(new Day());
  }
}

export const dayRoute: Routes = [
  {
    path: '',
    component: DayComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'skyfallApp.day.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: DayDetailComponent,
    resolve: {
      day: DayResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'skyfallApp.day.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: DayUpdateComponent,
    resolve: {
      day: DayResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'skyfallApp.day.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: DayUpdateComponent,
    resolve: {
      day: DayResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'skyfallApp.day.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
