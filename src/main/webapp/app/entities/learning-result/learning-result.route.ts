import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { LearningResult } from 'app/shared/model/learning-result.model';
import { LearningResultService } from './learning-result.service';
import { LearningResultComponent } from './learning-result.component';
import { LearningResultDetailComponent } from './learning-result-detail.component';
import { LearningResultUpdateComponent } from './learning-result-update.component';
import { ILearningResult } from 'app/shared/model/learning-result.model';

@Injectable({ providedIn: 'root' })
export class LearningResultResolve implements Resolve<ILearningResult> {
  constructor(private service: LearningResultService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ILearningResult> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((learningResult: HttpResponse<LearningResult>) => learningResult.body));
    }
    return of(new LearningResult());
  }
}

export const learningResultRoute: Routes = [
  {
    path: '',
    component: LearningResultComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'skyfallApp.learningResult.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: LearningResultDetailComponent,
    resolve: {
      learningResult: LearningResultResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'skyfallApp.learningResult.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: LearningResultUpdateComponent,
    resolve: {
      learningResult: LearningResultResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'skyfallApp.learningResult.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: LearningResultUpdateComponent,
    resolve: {
      learningResult: LearningResultResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'skyfallApp.learningResult.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
