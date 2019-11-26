import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Instructor } from 'app/shared/model/instructor.model';
import { InstructorService } from './instructor.service';
import { InstructorComponent } from './instructor.component';
import { InstructorDetailComponent } from './instructor-detail.component';
import { InstructorUpdateComponent } from './instructor-update.component';
import { IInstructor } from 'app/shared/model/instructor.model';

@Injectable({ providedIn: 'root' })
export class InstructorResolve implements Resolve<IInstructor> {
  constructor(private service: InstructorService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IInstructor> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((instructor: HttpResponse<Instructor>) => instructor.body));
    }
    return of(new Instructor());
  }
}

export const instructorRoute: Routes = [
  {
    path: '',
    component: InstructorComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'skyfallApp.instructor.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: InstructorDetailComponent,
    resolve: {
      instructor: InstructorResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'skyfallApp.instructor.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: InstructorUpdateComponent,
    resolve: {
      instructor: InstructorResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'skyfallApp.instructor.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: InstructorUpdateComponent,
    resolve: {
      instructor: InstructorResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'skyfallApp.instructor.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
