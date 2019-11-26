import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IResultSeen } from 'app/shared/model/result-seen.model';

type EntityResponseType = HttpResponse<IResultSeen>;
type EntityArrayResponseType = HttpResponse<IResultSeen[]>;

@Injectable({ providedIn: 'root' })
export class ResultSeenService {
  public resourceUrl = SERVER_API_URL + 'api/result-seens';

  constructor(protected http: HttpClient) {}

  create(resultSeen: IResultSeen): Observable<EntityResponseType> {
    return this.http.post<IResultSeen>(this.resourceUrl, resultSeen, { observe: 'response' });
  }

  update(resultSeen: IResultSeen): Observable<EntityResponseType> {
    return this.http.put<IResultSeen>(this.resourceUrl, resultSeen, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IResultSeen>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IResultSeen[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
