import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Competition } from 'app/shared/model/competition.model';
import { CompetitionService } from './competition.service';
import { CompetitionComponent } from './competition.component';
import { CompetitionDetailComponent } from './competition-detail.component';
import { CompetitionUpdateComponent } from './competition-update.component';
import { ICompetition } from 'app/shared/model/competition.model';

@Injectable({ providedIn: 'root' })
export class CompetitionResolve implements Resolve<ICompetition> {
  constructor(private service: CompetitionService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICompetition> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((competition: HttpResponse<Competition>) => competition.body));
    }
    return of(new Competition());
  }
}

export const competitionRoute: Routes = [
  {
    path: '',
    component: CompetitionComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'skyfallApp.competition.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: CompetitionDetailComponent,
    resolve: {
      competition: CompetitionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'skyfallApp.competition.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: CompetitionUpdateComponent,
    resolve: {
      competition: CompetitionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'skyfallApp.competition.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: CompetitionUpdateComponent,
    resolve: {
      competition: CompetitionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'skyfallApp.competition.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
