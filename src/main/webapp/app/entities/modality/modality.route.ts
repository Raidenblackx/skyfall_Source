import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Modality } from 'app/shared/model/modality.model';
import { ModalityService } from './modality.service';
import { ModalityComponent } from './modality.component';
import { ModalityDetailComponent } from './modality-detail.component';
import { ModalityUpdateComponent } from './modality-update.component';
import { IModality } from 'app/shared/model/modality.model';

@Injectable({ providedIn: 'root' })
export class ModalityResolve implements Resolve<IModality> {
  constructor(private service: ModalityService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IModality> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((modality: HttpResponse<Modality>) => modality.body));
    }
    return of(new Modality());
  }
}

export const modalityRoute: Routes = [
  {
    path: '',
    component: ModalityComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'skyfallApp.modality.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ModalityDetailComponent,
    resolve: {
      modality: ModalityResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'skyfallApp.modality.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ModalityUpdateComponent,
    resolve: {
      modality: ModalityResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'skyfallApp.modality.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ModalityUpdateComponent,
    resolve: {
      modality: ModalityResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'skyfallApp.modality.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
