import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICourseState } from 'app/shared/model/course-state.model';

type EntityResponseType = HttpResponse<ICourseState>;
type EntityArrayResponseType = HttpResponse<ICourseState[]>;

@Injectable({ providedIn: 'root' })
export class CourseStateService {
  public resourceUrl = SERVER_API_URL + 'api/course-states';

  constructor(protected http: HttpClient) {}

  create(courseState: ICourseState): Observable<EntityResponseType> {
    return this.http.post<ICourseState>(this.resourceUrl, courseState, { observe: 'response' });
  }

  update(courseState: ICourseState): Observable<EntityResponseType> {
    return this.http.put<ICourseState>(this.resourceUrl, courseState, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ICourseState>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICourseState[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
