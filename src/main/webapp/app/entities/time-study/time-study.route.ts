import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { TimeStudy } from 'app/shared/model/time-study.model';
import { TimeStudyService } from './time-study.service';
import { TimeStudyComponent } from './time-study.component';
import { TimeStudyDetailComponent } from './time-study-detail.component';
import { TimeStudyUpdateComponent } from './time-study-update.component';
import { ITimeStudy } from 'app/shared/model/time-study.model';

@Injectable({ providedIn: 'root' })
export class TimeStudyResolve implements Resolve<ITimeStudy> {
  constructor(private service: TimeStudyService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITimeStudy> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((timeStudy: HttpResponse<TimeStudy>) => timeStudy.body));
    }
    return of(new TimeStudy());
  }
}

export const timeStudyRoute: Routes = [
  {
    path: '',
    component: TimeStudyComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'skyfallApp.timeStudy.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: TimeStudyDetailComponent,
    resolve: {
      timeStudy: TimeStudyResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'skyfallApp.timeStudy.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: TimeStudyUpdateComponent,
    resolve: {
      timeStudy: TimeStudyResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'skyfallApp.timeStudy.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: TimeStudyUpdateComponent,
    resolve: {
      timeStudy: TimeStudyResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'skyfallApp.timeStudy.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
