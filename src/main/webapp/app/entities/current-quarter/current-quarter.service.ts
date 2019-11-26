import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICurrentQuarter } from 'app/shared/model/current-quarter.model';

type EntityResponseType = HttpResponse<ICurrentQuarter>;
type EntityArrayResponseType = HttpResponse<ICurrentQuarter[]>;

@Injectable({ providedIn: 'root' })
export class CurrentQuarterService {
  public resourceUrl = SERVER_API_URL + 'api/current-quarters';

  constructor(protected http: HttpClient) {}

  create(currentQuarter: ICurrentQuarter): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(currentQuarter);
    return this.http
      .post<ICurrentQuarter>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(currentQuarter: ICurrentQuarter): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(currentQuarter);
    return this.http
      .put<ICurrentQuarter>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ICurrentQuarter>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ICurrentQuarter[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(currentQuarter: ICurrentQuarter): ICurrentQuarter {
    const copy: ICurrentQuarter = Object.assign({}, currentQuarter, {
      startDate: currentQuarter.startDate != null && currentQuarter.startDate.isValid() ? currentQuarter.startDate.toJSON() : null,
      endDate: currentQuarter.endDate != null && currentQuarter.endDate.isValid() ? currentQuarter.endDate.toJSON() : null
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
      res.body.forEach((currentQuarter: ICurrentQuarter) => {
        currentQuarter.startDate = currentQuarter.startDate != null ? moment(currentQuarter.startDate) : null;
        currentQuarter.endDate = currentQuarter.endDate != null ? moment(currentQuarter.endDate) : null;
      });
    }
    return res;
  }
}
