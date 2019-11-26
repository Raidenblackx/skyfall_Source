import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Proyect } from 'app/shared/model/proyect.model';
import { ProyectService } from './proyect.service';
import { ProyectComponent } from './proyect.component';
import { ProyectDetailComponent } from './proyect-detail.component';
import { ProyectUpdateComponent } from './proyect-update.component';
import { IProyect } from 'app/shared/model/proyect.model';

@Injectable({ providedIn: 'root' })
export class ProyectResolve implements Resolve<IProyect> {
  constructor(private service: ProyectService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IProyect> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((proyect: HttpResponse<Proyect>) => proyect.body));
    }
    return of(new Proyect());
  }
}

export const proyectRoute: Routes = [
  {
    path: '',
    component: ProyectComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'skyfallApp.proyect.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ProyectDetailComponent,
    resolve: {
      proyect: ProyectResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'skyfallApp.proyect.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ProyectUpdateComponent,
    resolve: {
      proyect: ProyectResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'skyfallApp.proyect.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ProyectUpdateComponent,
    resolve: {
      proyect: ProyectResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'skyfallApp.proyect.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
