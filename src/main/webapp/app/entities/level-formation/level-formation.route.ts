import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { LevelFormation } from 'app/shared/model/level-formation.model';
import { LevelFormationService } from './level-formation.service';
import { LevelFormationComponent } from './level-formation.component';
import { LevelFormationDetailComponent } from './level-formation-detail.component';
import { LevelFormationUpdateComponent } from './level-formation-update.component';
import { ILevelFormation } from 'app/shared/model/level-formation.model';

@Injectable({ providedIn: 'root' })
export class LevelFormationResolve implements Resolve<ILevelFormation> {
  constructor(private service: LevelFormationService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ILevelFormation> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((levelFormation: HttpResponse<LevelFormation>) => levelFormation.body));
    }
    return of(new LevelFormation());
  }
}

export const levelFormationRoute: Routes = [
  {
    path: '',
    component: LevelFormationComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'skyfallApp.levelFormation.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: LevelFormationDetailComponent,
    resolve: {
      levelFormation: LevelFormationResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'skyfallApp.levelFormation.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: LevelFormationUpdateComponent,
    resolve: {
      levelFormation: LevelFormationResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'skyfallApp.levelFormation.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: LevelFormationUpdateComponent,
    resolve: {
      levelFormation: LevelFormationResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'skyfallApp.levelFormation.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
