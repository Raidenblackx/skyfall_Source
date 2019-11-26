import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Linkage } from 'app/shared/model/linkage.model';
import { LinkageService } from './linkage.service';
import { LinkageComponent } from './linkage.component';
import { LinkageDetailComponent } from './linkage-detail.component';
import { LinkageUpdateComponent } from './linkage-update.component';
import { ILinkage } from 'app/shared/model/linkage.model';

@Injectable({ providedIn: 'root' })
export class LinkageResolve implements Resolve<ILinkage> {
  constructor(private service: LinkageService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ILinkage> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((linkage: HttpResponse<Linkage>) => linkage.body));
    }
    return of(new Linkage());
  }
}

export const linkageRoute: Routes = [
  {
    path: '',
    component: LinkageComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'skyfallApp.linkage.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: LinkageDetailComponent,
    resolve: {
      linkage: LinkageResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'skyfallApp.linkage.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: LinkageUpdateComponent,
    resolve: {
      linkage: LinkageResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'skyfallApp.linkage.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: LinkageUpdateComponent,
    resolve: {
      linkage: LinkageResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'skyfallApp.linkage.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
