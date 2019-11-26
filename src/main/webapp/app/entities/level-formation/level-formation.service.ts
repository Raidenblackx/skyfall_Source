import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ILevelFormation } from 'app/shared/model/level-formation.model';

type EntityResponseType = HttpResponse<ILevelFormation>;
type EntityArrayResponseType = HttpResponse<ILevelFormation[]>;

@Injectable({ providedIn: 'root' })
export class LevelFormationService {
  public resourceUrl = SERVER_API_URL + 'api/level-formations';

  constructor(protected http: HttpClient) {}

  create(levelFormation: ILevelFormation): Observable<EntityResponseType> {
    return this.http.post<ILevelFormation>(this.resourceUrl, levelFormation, { observe: 'response' });
  }

  update(levelFormation: ILevelFormation): Observable<EntityResponseType> {
    return this.http.put<ILevelFormation>(this.resourceUrl, levelFormation, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ILevelFormation>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ILevelFormation[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
