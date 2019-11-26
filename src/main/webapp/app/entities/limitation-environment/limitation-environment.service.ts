import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ILimitationEnvironment } from 'app/shared/model/limitation-environment.model';

type EntityResponseType = HttpResponse<ILimitationEnvironment>;
type EntityArrayResponseType = HttpResponse<ILimitationEnvironment[]>;

@Injectable({ providedIn: 'root' })
export class LimitationEnvironmentService {
  public resourceUrl = SERVER_API_URL + 'api/limitation-environments';

  constructor(protected http: HttpClient) {}

  create(limitationEnvironment: ILimitationEnvironment): Observable<EntityResponseType> {
    return this.http.post<ILimitationEnvironment>(this.resourceUrl, limitationEnvironment, { observe: 'response' });
  }

  update(limitationEnvironment: ILimitationEnvironment): Observable<EntityResponseType> {
    return this.http.put<ILimitationEnvironment>(this.resourceUrl, limitationEnvironment, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ILimitationEnvironment>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ILimitationEnvironment[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
