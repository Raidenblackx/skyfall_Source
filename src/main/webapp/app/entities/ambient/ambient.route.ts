import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Ambient } from 'app/shared/model/ambient.model';
import { AmbientService } from './ambient.service';
import { AmbientComponent } from './ambient.component';
import { AmbientDetailComponent } from './ambient-detail.component';
import { AmbientUpdateComponent } from './ambient-update.component';
import { IAmbient } from 'app/shared/model/ambient.model';

@Injectable({ providedIn: 'root' })
export class AmbientResolve implements Resolve<IAmbient> {
  constructor(private service: AmbientService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IAmbient> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((ambient: HttpResponse<Ambient>) => ambient.body));
    }
    return of(new Ambient());
  }
}

export const ambientRoute: Routes = [
  {
    path: '',
    component: AmbientComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'skyfallApp.ambient.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: AmbientDetailComponent,
    resolve: {
      ambient: AmbientResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'skyfallApp.ambient.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: AmbientUpdateComponent,
    resolve: {
      ambient: AmbientResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'skyfallApp.ambient.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: AmbientUpdateComponent,
    resolve: {
      ambient: AmbientResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'skyfallApp.ambient.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
