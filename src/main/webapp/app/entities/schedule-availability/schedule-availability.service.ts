import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IScheduleAvailability } from 'app/shared/model/schedule-availability.model';

type EntityResponseType = HttpResponse<IScheduleAvailability>;
type EntityArrayResponseType = HttpResponse<IScheduleAvailability[]>;

@Injectable({ providedIn: 'root' })
export class ScheduleAvailabilityService {
  public resourceUrl = SERVER_API_URL + 'api/schedule-availabilities';

  constructor(protected http: HttpClient) {}

  create(scheduleAvailability: IScheduleAvailability): Observable<EntityResponseType> {
    return this.http.post<IScheduleAvailability>(this.resourceUrl, scheduleAvailability, { observe: 'response' });
  }

  update(scheduleAvailability: IScheduleAvailability): Observable<EntityResponseType> {
    return this.http.put<IScheduleAvailability>(this.resourceUrl, scheduleAvailability, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IScheduleAvailability>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IScheduleAvailability[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
