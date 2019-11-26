import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Program } from 'app/shared/model/program.model';
import { ProgramService } from './program.service';
import { ProgramComponent } from './program.component';
import { ProgramDetailComponent } from './program-detail.component';
import { ProgramUpdateComponent } from './program-update.component';
import { IProgram } from 'app/shared/model/program.model';

@Injectable({ providedIn: 'root' })
export class ProgramResolve implements Resolve<IProgram> {
  constructor(private service: ProgramService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IProgram> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((program: HttpResponse<Program>) => program.body));
    }
    return of(new Program());
  }
}

export const programRoute: Routes = [
  {
    path: '',
    component: ProgramComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'skyfallApp.program.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ProgramDetailComponent,
    resolve: {
      program: ProgramResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'skyfallApp.program.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ProgramUpdateComponent,
    resolve: {
      program: ProgramResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'skyfallApp.program.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ProgramUpdateComponent,
    resolve: {
      program: ProgramResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'skyfallApp.program.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
