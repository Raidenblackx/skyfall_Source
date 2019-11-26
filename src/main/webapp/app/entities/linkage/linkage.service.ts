import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ILinkage } from 'app/shared/model/linkage.model';

type EntityResponseType = HttpResponse<ILinkage>;
type EntityArrayResponseType = HttpResponse<ILinkage[]>;

@Injectable({ providedIn: 'root' })
export class LinkageService {
  public resourceUrl = SERVER_API_URL + 'api/linkages';

  constructor(protected http: HttpClient) {}

  create(linkage: ILinkage): Observable<EntityResponseType> {
    return this.http.post<ILinkage>(this.resourceUrl, linkage, { observe: 'response' });
  }

  update(linkage: ILinkage): Observable<EntityResponseType> {
    return this.http.put<ILinkage>(this.resourceUrl, linkage, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ILinkage>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ILinkage[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
