import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IWorkingDay } from 'app/shared/model/working-day.model';

type EntityResponseType = HttpResponse<IWorkingDay>;
type EntityArrayResponseType = HttpResponse<IWorkingDay[]>;

@Injectable({ providedIn: 'root' })
export class WorkingDayService {
  public resourceUrl = SERVER_API_URL + 'api/working-days';

  constructor(protected http: HttpClient) {}

  create(workingDay: IWorkingDay): Observable<EntityResponseType> {
    return this.http.post<IWorkingDay>(this.resourceUrl, workingDay, { observe: 'response' });
  }

  update(workingDay: IWorkingDay): Observable<EntityResponseType> {
    return this.http.put<IWorkingDay>(this.resourceUrl, workingDay, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IWorkingDay>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IWorkingDay[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
