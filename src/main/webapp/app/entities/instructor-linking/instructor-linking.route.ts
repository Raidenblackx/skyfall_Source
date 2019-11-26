import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { InstructorLinking } from 'app/shared/model/instructor-linking.model';
import { InstructorLinkingService } from './instructor-linking.service';
import { InstructorLinkingComponent } from './instructor-linking.component';
import { InstructorLinkingDetailComponent } from './instructor-linking-detail.component';
import { InstructorLinkingUpdateComponent } from './instructor-linking-update.component';
import { IInstructorLinking } from 'app/shared/model/instructor-linking.model';

@Injectable({ providedIn: 'root' })
export class InstructorLinkingResolve implements Resolve<IInstructorLinking> {
  constructor(private service: InstructorLinkingService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IInstructorLinking> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((instructorLinking: HttpResponse<InstructorLinking>) => instructorLinking.body));
    }
    return of(new InstructorLinking());
  }
}

export const instructorLinkingRoute: Routes = [
  {
    path: '',
    component: InstructorLinkingComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'skyfallApp.instructorLinking.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: InstructorLinkingDetailComponent,
    resolve: {
      instructorLinking: InstructorLinkingResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'skyfallApp.instructorLinking.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: InstructorLinkingUpdateComponent,
    resolve: {
      instructorLinking: InstructorLinkingResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'skyfallApp.instructorLinking.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: InstructorLinkingUpdateComponent,
    resolve: {
      instructorLinking: InstructorLinkingResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'skyfallApp.instructorLinking.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
