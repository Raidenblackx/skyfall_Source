import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { ProyectActivity } from 'app/shared/model/proyect-activity.model';
import { ProyectActivityService } from './proyect-activity.service';
import { ProyectActivityComponent } from './proyect-activity.component';
import { ProyectActivityDetailComponent } from './proyect-activity-detail.component';
import { ProyectActivityUpdateComponent } from './proyect-activity-update.component';
import { IProyectActivity } from 'app/shared/model/proyect-activity.model';

@Injectable({ providedIn: 'root' })
export class ProyectActivityResolve implements Resolve<IProyectActivity> {
  constructor(private service: ProyectActivityService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IProyectActivity> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((proyectActivity: HttpResponse<ProyectActivity>) => proyectActivity.body));
    }
    return of(new ProyectActivity());
  }
}

export const proyectActivityRoute: Routes = [
  {
    path: '',
    component: ProyectActivityComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'skyfallApp.proyectActivity.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ProyectActivityDetailComponent,
    resolve: {
      proyectActivity: ProyectActivityResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'skyfallApp.proyectActivity.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ProyectActivityUpdateComponent,
    resolve: {
      proyectActivity: ProyectActivityResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'skyfallApp.proyectActivity.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ProyectActivityUpdateComponent,
    resolve: {
      proyectActivity: ProyectActivityResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'skyfallApp.proyectActivity.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
