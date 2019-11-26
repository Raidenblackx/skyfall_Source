import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITypeEnvironment } from 'app/shared/model/type-environment.model';

type EntityResponseType = HttpResponse<ITypeEnvironment>;
type EntityArrayResponseType = HttpResponse<ITypeEnvironment[]>;

@Injectable({ providedIn: 'root' })
export class TypeEnvironmentService {
  public resourceUrl = SERVER_API_URL + 'api/type-environments';

  constructor(protected http: HttpClient) {}

  create(typeEnvironment: ITypeEnvironment): Observable<EntityResponseType> {
    return this.http.post<ITypeEnvironment>(this.resourceUrl, typeEnvironment, { observe: 'response' });
  }

  update(typeEnvironment: ITypeEnvironment): Observable<EntityResponseType> {
    return this.http.put<ITypeEnvironment>(this.resourceUrl, typeEnvironment, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITypeEnvironment>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITypeEnvironment[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
