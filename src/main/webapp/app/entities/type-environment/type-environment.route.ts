import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { TypeEnvironment } from 'app/shared/model/type-environment.model';
import { TypeEnvironmentService } from './type-environment.service';
import { TypeEnvironmentComponent } from './type-environment.component';
import { TypeEnvironmentDetailComponent } from './type-environment-detail.component';
import { TypeEnvironmentUpdateComponent } from './type-environment-update.component';
import { ITypeEnvironment } from 'app/shared/model/type-environment.model';

@Injectable({ providedIn: 'root' })
export class TypeEnvironmentResolve implements Resolve<ITypeEnvironment> {
  constructor(private service: TypeEnvironmentService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITypeEnvironment> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((typeEnvironment: HttpResponse<TypeEnvironment>) => typeEnvironment.body));
    }
    return of(new TypeEnvironment());
  }
}

export const typeEnvironmentRoute: Routes = [
  {
    path: '',
    component: TypeEnvironmentComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'skyfallApp.typeEnvironment.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: TypeEnvironmentDetailComponent,
    resolve: {
      typeEnvironment: TypeEnvironmentResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'skyfallApp.typeEnvironment.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: TypeEnvironmentUpdateComponent,
    resolve: {
      typeEnvironment: TypeEnvironmentResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'skyfallApp.typeEnvironment.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: TypeEnvironmentUpdateComponent,
    resolve: {
      typeEnvironment: TypeEnvironmentResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'skyfallApp.typeEnvironment.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
