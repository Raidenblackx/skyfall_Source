import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IScheduleVersion } from 'app/shared/model/schedule-version.model';

type EntityResponseType = HttpResponse<IScheduleVersion>;
type EntityArrayResponseType = HttpResponse<IScheduleVersion[]>;

@Injectable({ providedIn: 'root' })
export class ScheduleVersionService {
  public resourceUrl = SERVER_API_URL + 'api/schedule-versions';

  constructor(protected http: HttpClient) {}

  create(scheduleVersion: IScheduleVersion): Observable<EntityResponseType> {
    return this.http.post<IScheduleVersion>(this.resourceUrl, scheduleVersion, { observe: 'response' });
  }

  update(scheduleVersion: IScheduleVersion): Observable<EntityResponseType> {
    return this.http.put<IScheduleVersion>(this.resourceUrl, scheduleVersion, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IScheduleVersion>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IScheduleVersion[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
