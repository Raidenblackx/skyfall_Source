import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IAmbient } from 'app/shared/model/ambient.model';

type EntityResponseType = HttpResponse<IAmbient>;
type EntityArrayResponseType = HttpResponse<IAmbient[]>;

@Injectable({ providedIn: 'root' })
export class AmbientService {
  public resourceUrl = SERVER_API_URL + 'api/ambients';

  constructor(protected http: HttpClient) {}

  create(ambient: IAmbient): Observable<EntityResponseType> {
    return this.http.post<IAmbient>(this.resourceUrl, ambient, { observe: 'response' });
  }

  update(ambient: IAmbient): Observable<EntityResponseType> {
    return this.http.put<IAmbient>(this.resourceUrl, ambient, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IAmbient>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IAmbient[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
