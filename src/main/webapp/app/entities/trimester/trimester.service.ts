import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITrimester } from 'app/shared/model/trimester.model';

type EntityResponseType = HttpResponse<ITrimester>;
type EntityArrayResponseType = HttpResponse<ITrimester[]>;

@Injectable({ providedIn: 'root' })
export class TrimesterService {
  public resourceUrl = SERVER_API_URL + 'api/trimesters';

  constructor(protected http: HttpClient) {}

  create(trimester: ITrimester): Observable<EntityResponseType> {
    return this.http.post<ITrimester>(this.resourceUrl, trimester, { observe: 'response' });
  }

  update(trimester: ITrimester): Observable<EntityResponseType> {
    return this.http.put<ITrimester>(this.resourceUrl, trimester, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITrimester>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITrimester[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
