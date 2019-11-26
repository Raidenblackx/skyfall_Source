import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Phase } from 'app/shared/model/phase.model';
import { PhaseService } from './phase.service';
import { PhaseComponent } from './phase.component';
import { PhaseDetailComponent } from './phase-detail.component';
import { PhaseUpdateComponent } from './phase-update.component';
import { IPhase } from 'app/shared/model/phase.model';

@Injectable({ providedIn: 'root' })
export class PhaseResolve implements Resolve<IPhase> {
  constructor(private service: PhaseService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IPhase> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((phase: HttpResponse<Phase>) => phase.body));
    }
    return of(new Phase());
  }
}

export const phaseRoute: Routes = [
  {
    path: '',
    component: PhaseComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'skyfallApp.phase.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: PhaseDetailComponent,
    resolve: {
      phase: PhaseResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'skyfallApp.phase.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: PhaseUpdateComponent,
    resolve: {
      phase: PhaseResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'skyfallApp.phase.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: PhaseUpdateComponent,
    resolve: {
      phase: PhaseResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'skyfallApp.phase.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
