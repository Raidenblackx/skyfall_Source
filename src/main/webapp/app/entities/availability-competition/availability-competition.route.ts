import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { AvailabilityCompetition } from 'app/shared/model/availability-competition.model';
import { AvailabilityCompetitionService } from './availability-competition.service';
import { AvailabilityCompetitionComponent } from './availability-competition.component';
import { AvailabilityCompetitionDetailComponent } from './availability-competition-detail.component';
import { AvailabilityCompetitionUpdateComponent } from './availability-competition-update.component';
import { IAvailabilityCompetition } from 'app/shared/model/availability-competition.model';

@Injectable({ providedIn: 'root' })
export class AvailabilityCompetitionResolve implements Resolve<IAvailabilityCompetition> {
  constructor(private service: AvailabilityCompetitionService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IAvailabilityCompetition> {
    const id = route.params['id'];
    if (id) {
      return this.service
        .find(id)
        .pipe(map((availabilityCompetition: HttpResponse<AvailabilityCompetition>) => availabilityCompetition.body));
    }
    return of(new AvailabilityCompetition());
  }
}

export const availabilityCompetitionRoute: Routes = [
  {
    path: '',
    component: AvailabilityCompetitionComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'skyfallApp.availabilityCompetition.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: AvailabilityCompetitionDetailComponent,
    resolve: {
      availabilityCompetition: AvailabilityCompetitionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'skyfallApp.availabilityCompetition.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: AvailabilityCompetitionUpdateComponent,
    resolve: {
      availabilityCompetition: AvailabilityCompetitionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'skyfallApp.availabilityCompetition.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: AvailabilityCompetitionUpdateComponent,
    resolve: {
      availabilityCompetition: AvailabilityCompetitionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'skyfallApp.availabilityCompetition.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
