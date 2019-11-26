import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IProyectActivity } from 'app/shared/model/proyect-activity.model';

type EntityResponseType = HttpResponse<IProyectActivity>;
type EntityArrayResponseType = HttpResponse<IProyectActivity[]>;

@Injectable({ providedIn: 'root' })
export class ProyectActivityService {
  public resourceUrl = SERVER_API_URL + 'api/proyect-activities';

  constructor(protected http: HttpClient) {}

  create(proyectActivity: IProyectActivity): Observable<EntityResponseType> {
    return this.http.post<IProyectActivity>(this.resourceUrl, proyectActivity, { observe: 'response' });
  }

  update(proyectActivity: IProyectActivity): Observable<EntityResponseType> {
    return this.http.put<IProyectActivity>(this.resourceUrl, proyectActivity, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IProyectActivity>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IProyectActivity[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
