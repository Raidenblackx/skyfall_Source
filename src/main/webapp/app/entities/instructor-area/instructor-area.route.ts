import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { InstructorArea } from 'app/shared/model/instructor-area.model';
import { InstructorAreaService } from './instructor-area.service';
import { InstructorAreaComponent } from './instructor-area.component';
import { InstructorAreaDetailComponent } from './instructor-area-detail.component';
import { InstructorAreaUpdateComponent } from './instructor-area-update.component';
import { IInstructorArea } from 'app/shared/model/instructor-area.model';

@Injectable({ providedIn: 'root' })
export class InstructorAreaResolve implements Resolve<IInstructorArea> {
  constructor(private service: InstructorAreaService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IInstructorArea> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((instructorArea: HttpResponse<InstructorArea>) => instructorArea.body));
    }
    return of(new InstructorArea());
  }
}

export const instructorAreaRoute: Routes = [
  {
    path: '',
    component: InstructorAreaComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'skyfallApp.instructorArea.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: InstructorAreaDetailComponent,
    resolve: {
      instructorArea: InstructorAreaResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'skyfallApp.instructorArea.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: InstructorAreaUpdateComponent,
    resolve: {
      instructorArea: InstructorAreaResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'skyfallApp.instructorArea.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: InstructorAreaUpdateComponent,
    resolve: {
      instructorArea: InstructorAreaResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'skyfallApp.instructorArea.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
