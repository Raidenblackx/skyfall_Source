import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICourseHasTrimester } from 'app/shared/model/course-has-trimester.model';

type EntityResponseType = HttpResponse<ICourseHasTrimester>;
type EntityArrayResponseType = HttpResponse<ICourseHasTrimester[]>;

@Injectable({ providedIn: 'root' })
export class CourseHasTrimesterService {
  public resourceUrl = SERVER_API_URL + 'api/course-has-trimesters';

  constructor(protected http: HttpClient) {}

  create(courseHasTrimester: ICourseHasTrimester): Observable<EntityResponseType> {
    return this.http.post<ICourseHasTrimester>(this.resourceUrl, courseHasTrimester, { observe: 'response' });
  }

  update(courseHasTrimester: ICourseHasTrimester): Observable<EntityResponseType> {
    return this.http.put<ICourseHasTrimester>(this.resourceUrl, courseHasTrimester, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ICourseHasTrimester>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICourseHasTrimester[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
