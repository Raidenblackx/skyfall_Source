import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { CourseState } from 'app/shared/model/course-state.model';
import { CourseStateService } from './course-state.service';
import { CourseStateComponent } from './course-state.component';
import { CourseStateDetailComponent } from './course-state-detail.component';
import { CourseStateUpdateComponent } from './course-state-update.component';
import { ICourseState } from 'app/shared/model/course-state.model';

@Injectable({ providedIn: 'root' })
export class CourseStateResolve implements Resolve<ICourseState> {
  constructor(private service: CourseStateService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICourseState> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((courseState: HttpResponse<CourseState>) => courseState.body));
    }
    return of(new CourseState());
  }
}

export const courseStateRoute: Routes = [
  {
    path: '',
    component: CourseStateComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'skyfallApp.courseState.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: CourseStateDetailComponent,
    resolve: {
      courseState: CourseStateResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'skyfallApp.courseState.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: CourseStateUpdateComponent,
    resolve: {
      courseState: CourseStateResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'skyfallApp.courseState.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: CourseStateUpdateComponent,
    resolve: {
      courseState: CourseStateResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'skyfallApp.courseState.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
