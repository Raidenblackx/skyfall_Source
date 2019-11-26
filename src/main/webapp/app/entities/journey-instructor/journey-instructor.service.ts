import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IJourneyInstructor } from 'app/shared/model/journey-instructor.model';

type EntityResponseType = HttpResponse<IJourneyInstructor>;
type EntityArrayResponseType = HttpResponse<IJourneyInstructor[]>;

@Injectable({ providedIn: 'root' })
export class JourneyInstructorService {
  public resourceUrl = SERVER_API_URL + 'api/journey-instructors';

  constructor(protected http: HttpClient) {}

  create(journeyInstructor: IJourneyInstructor): Observable<EntityResponseType> {
    return this.http.post<IJourneyInstructor>(this.resourceUrl, journeyInstructor, { observe: 'response' });
  }

  update(journeyInstructor: IJourneyInstructor): Observable<EntityResponseType> {
    return this.http.put<IJourneyInstructor>(this.resourceUrl, journeyInstructor, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IJourneyInstructor>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IJourneyInstructor[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
