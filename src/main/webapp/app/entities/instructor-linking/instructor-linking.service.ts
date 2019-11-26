import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IInstructorLinking } from 'app/shared/model/instructor-linking.model';

type EntityResponseType = HttpResponse<IInstructorLinking>;
type EntityArrayResponseType = HttpResponse<IInstructorLinking[]>;

@Injectable({ providedIn: 'root' })
export class InstructorLinkingService {
  public resourceUrl = SERVER_API_URL + 'api/instructor-linkings';

  constructor(protected http: HttpClient) {}

  create(instructorLinking: IInstructorLinking): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(instructorLinking);
    return this.http
      .post<IInstructorLinking>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(instructorLinking: IInstructorLinking): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(instructorLinking);
    return this.http
      .put<IInstructorLinking>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IInstructorLinking>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IInstructorLinking[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(instructorLinking: IInstructorLinking): IInstructorLinking {
    const copy: IInstructorLinking = Object.assign({}, instructorLinking, {
      startDate: instructorLinking.startDate != null && instructorLinking.startDate.isValid() ? instructorLinking.startDate.toJSON() : null,
      endDate: instructorLinking.endDate != null && instructorLinking.endDate.isValid() ? instructorLinking.endDate.toJSON() : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.startDate = res.body.startDate != null ? moment(res.body.startDate) : null;
      res.body.endDate = res.body.endDate != null ? moment(res.body.endDate) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((instructorLinking: IInstructorLinking) => {
        instructorLinking.startDate = instructorLinking.startDate != null ? moment(instructorLinking.startDate) : null;
        instructorLinking.endDate = instructorLinking.endDate != null ? moment(instructorLinking.endDate) : null;
      });
    }
    return res;
  }
}
