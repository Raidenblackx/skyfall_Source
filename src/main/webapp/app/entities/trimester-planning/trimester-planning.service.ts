import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITrimesterPlanning } from 'app/shared/model/trimester-planning.model';

type EntityResponseType = HttpResponse<ITrimesterPlanning>;
type EntityArrayResponseType = HttpResponse<ITrimesterPlanning[]>;

@Injectable({ providedIn: 'root' })
export class TrimesterPlanningService {
  public resourceUrl = SERVER_API_URL + 'api/trimester-plannings';

  constructor(protected http: HttpClient) {}

  create(trimesterPlanning: ITrimesterPlanning): Observable<EntityResponseType> {
    return this.http.post<ITrimesterPlanning>(this.resourceUrl, trimesterPlanning, { observe: 'response' });
  }

  update(trimesterPlanning: ITrimesterPlanning): Observable<EntityResponseType> {
    return this.http.put<ITrimesterPlanning>(this.resourceUrl, trimesterPlanning, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITrimesterPlanning>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITrimesterPlanning[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
