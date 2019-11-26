import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { CourseHasTrimester } from 'app/shared/model/course-has-trimester.model';
import { CourseHasTrimesterService } from './course-has-trimester.service';
import { CourseHasTrimesterComponent } from './course-has-trimester.component';
import { CourseHasTrimesterDetailComponent } from './course-has-trimester-detail.component';
import { CourseHasTrimesterUpdateComponent } from './course-has-trimester-update.component';
import { ICourseHasTrimester } from 'app/shared/model/course-has-trimester.model';

@Injectable({ providedIn: 'root' })
export class CourseHasTrimesterResolve implements Resolve<ICourseHasTrimester> {
  constructor(private service: CourseHasTrimesterService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICourseHasTrimester> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((courseHasTrimester: HttpResponse<CourseHasTrimester>) => courseHasTrimester.body));
    }
    return of(new CourseHasTrimester());
  }
}

export const courseHasTrimesterRoute: Routes = [
  {
    path: '',
    component: CourseHasTrimesterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'skyfallApp.courseHasTrimester.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: CourseHasTrimesterDetailComponent,
    resolve: {
      courseHasTrimester: CourseHasTrimesterResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'skyfallApp.courseHasTrimester.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: CourseHasTrimesterUpdateComponent,
    resolve: {
      courseHasTrimester: CourseHasTrimesterResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'skyfallApp.courseHasTrimester.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: CourseHasTrimesterUpdateComponent,
    resolve: {
      courseHasTrimester: CourseHasTrimesterResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'skyfallApp.courseHasTrimester.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
