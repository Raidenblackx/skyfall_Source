import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IPlanningActivity } from 'app/shared/model/planning-activity.model';

type EntityResponseType = HttpResponse<IPlanningActivity>;
type EntityArrayResponseType = HttpResponse<IPlanningActivity[]>;

@Injectable({ providedIn: 'root' })
export class PlanningActivityService {
  public resourceUrl = SERVER_API_URL + 'api/planning-activities';

  constructor(protected http: HttpClient) {}

  create(planningActivity: IPlanningActivity): Observable<EntityResponseType> {
    return this.http.post<IPlanningActivity>(this.resourceUrl, planningActivity, { observe: 'response' });
  }

  update(planningActivity: IPlanningActivity): Observable<EntityResponseType> {
    return this.http.put<IPlanningActivity>(this.resourceUrl, planningActivity, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IPlanningActivity>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IPlanningActivity[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
