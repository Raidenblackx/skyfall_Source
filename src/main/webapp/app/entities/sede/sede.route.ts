import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Sede } from 'app/shared/model/sede.model';
import { SedeService } from './sede.service';
import { SedeComponent } from './sede.component';
import { SedeDetailComponent } from './sede-detail.component';
import { SedeUpdateComponent } from './sede-update.component';
import { ISede } from 'app/shared/model/sede.model';

@Injectable({ providedIn: 'root' })
export class SedeResolve implements Resolve<ISede> {
  constructor(private service: SedeService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ISede> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((sede: HttpResponse<Sede>) => sede.body));
    }
    return of(new Sede());
  }
}

export const sedeRoute: Routes = [
  {
    path: '',
    component: SedeComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'skyfallApp.sede.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: SedeDetailComponent,
    resolve: {
      sede: SedeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'skyfallApp.sede.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: SedeUpdateComponent,
    resolve: {
      sede: SedeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'skyfallApp.sede.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: SedeUpdateComponent,
    resolve: {
      sede: SedeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'skyfallApp.sede.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
