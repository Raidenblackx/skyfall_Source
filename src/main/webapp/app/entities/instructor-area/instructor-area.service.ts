import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IInstructorArea } from 'app/shared/model/instructor-area.model';

type EntityResponseType = HttpResponse<IInstructorArea>;
type EntityArrayResponseType = HttpResponse<IInstructorArea[]>;

@Injectable({ providedIn: 'root' })
export class InstructorAreaService {
  public resourceUrl = SERVER_API_URL + 'api/instructor-areas';

  constructor(protected http: HttpClient) {}

  create(instructorArea: IInstructorArea): Observable<EntityResponseType> {
    return this.http.post<IInstructorArea>(this.resourceUrl, instructorArea, { observe: 'response' });
  }

  update(instructorArea: IInstructorArea): Observable<EntityResponseType> {
    return this.http.put<IInstructorArea>(this.resourceUrl, instructorArea, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IInstructorArea>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IInstructorArea[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
