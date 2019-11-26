import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ILearningResult } from 'app/shared/model/learning-result.model';

type EntityResponseType = HttpResponse<ILearningResult>;
type EntityArrayResponseType = HttpResponse<ILearningResult[]>;

@Injectable({ providedIn: 'root' })
export class LearningResultService {
  public resourceUrl = SERVER_API_URL + 'api/learning-results';

  constructor(protected http: HttpClient) {}

  create(learningResult: ILearningResult): Observable<EntityResponseType> {
    return this.http.post<ILearningResult>(this.resourceUrl, learningResult, { observe: 'response' });
  }

  update(learningResult: ILearningResult): Observable<EntityResponseType> {
    return this.http.put<ILearningResult>(this.resourceUrl, learningResult, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ILearningResult>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ILearningResult[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
