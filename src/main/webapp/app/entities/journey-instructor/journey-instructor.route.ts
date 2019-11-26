import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { JourneyInstructor } from 'app/shared/model/journey-instructor.model';
import { JourneyInstructorService } from './journey-instructor.service';
import { JourneyInstructorComponent } from './journey-instructor.component';
import { JourneyInstructorDetailComponent } from './journey-instructor-detail.component';
import { JourneyInstructorUpdateComponent } from './journey-instructor-update.component';
import { IJourneyInstructor } from 'app/shared/model/journey-instructor.model';

@Injectable({ providedIn: 'root' })
export class JourneyInstructorResolve implements Resolve<IJourneyInstructor> {
  constructor(private service: JourneyInstructorService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IJourneyInstructor> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((journeyInstructor: HttpResponse<JourneyInstructor>) => journeyInstructor.body));
    }
    return of(new JourneyInstructor());
  }
}

export const journeyInstructorRoute: Routes = [
  {
    path: '',
    component: JourneyInstructorComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'skyfallApp.journeyInstructor.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: JourneyInstructorDetailComponent,
    resolve: {
      journeyInstructor: JourneyInstructorResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'skyfallApp.journeyInstructor.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: JourneyInstructorUpdateComponent,
    resolve: {
      journeyInstructor: JourneyInstructorResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'skyfallApp.journeyInstructor.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: JourneyInstructorUpdateComponent,
    resolve: {
      journeyInstructor: JourneyInstructorResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'skyfallApp.journeyInstructor.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
