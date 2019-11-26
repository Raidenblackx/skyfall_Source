import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IProyect } from 'app/shared/model/proyect.model';

type EntityResponseType = HttpResponse<IProyect>;
type EntityArrayResponseType = HttpResponse<IProyect[]>;

@Injectable({ providedIn: 'root' })
export class ProyectService {
  public resourceUrl = SERVER_API_URL + 'api/proyects';

  constructor(protected http: HttpClient) {}

  create(proyect: IProyect): Observable<EntityResponseType> {
    return this.http.post<IProyect>(this.resourceUrl, proyect, { observe: 'response' });
  }

  update(proyect: IProyect): Observable<EntityResponseType> {
    return this.http.put<IProyect>(this.resourceUrl, proyect, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IProyect>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IProyect[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
