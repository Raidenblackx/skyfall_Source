import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IPhase } from 'app/shared/model/phase.model';

type EntityResponseType = HttpResponse<IPhase>;
type EntityArrayResponseType = HttpResponse<IPhase[]>;

@Injectable({ providedIn: 'root' })
export class PhaseService {
  public resourceUrl = SERVER_API_URL + 'api/phases';

  constructor(protected http: HttpClient) {}

  create(phase: IPhase): Observable<EntityResponseType> {
    return this.http.post<IPhase>(this.resourceUrl, phase, { observe: 'response' });
  }

  update(phase: IPhase): Observable<EntityResponseType> {
    return this.http.put<IPhase>(this.resourceUrl, phase, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IPhase>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IPhase[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
