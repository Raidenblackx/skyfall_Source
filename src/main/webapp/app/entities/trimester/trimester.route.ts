import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Trimester } from 'app/shared/model/trimester.model';
import { TrimesterService } from './trimester.service';
import { TrimesterComponent } from './trimester.component';
import { TrimesterDetailComponent } from './trimester-detail.component';
import { TrimesterUpdateComponent } from './trimester-update.component';
import { ITrimester } from 'app/shared/model/trimester.model';

@Injectable({ providedIn: 'root' })
export class TrimesterResolve implements Resolve<ITrimester> {
  constructor(private service: TrimesterService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITrimester> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((trimester: HttpResponse<Trimester>) => trimester.body));
    }
    return of(new Trimester());
  }
}

export const trimesterRoute: Routes = [
  {
    path: '',
    component: TrimesterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'skyfallApp.trimester.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: TrimesterDetailComponent,
    resolve: {
      trimester: TrimesterResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'skyfallApp.trimester.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: TrimesterUpdateComponent,
    resolve: {
      trimester: TrimesterResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'skyfallApp.trimester.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: TrimesterUpdateComponent,
    resolve: {
      trimester: TrimesterResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'skyfallApp.trimester.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
