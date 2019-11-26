import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import { ProgramService } from 'app/entities/program/program.service';
import { IProgram, Program } from 'app/shared/model/program.model';
import { StateProgram } from 'app/shared/model/enumerations/state-program.model';

describe('Service Tests', () => {
  describe('Program Service', () => {
    let injector: TestBed;
    let service: ProgramService;
    let httpMock: HttpTestingController;
    let elemDefault: IProgram;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(ProgramService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new Program(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', StateProgram.EJECUCION);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);
        service
          .find(123)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: elemDefault });
      });

      it('should create a Program', () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new Program(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a Program', () => {
        const returnedFromService = Object.assign(
          {
            codeProgram: 'BBBBBB',
            version: 'BBBBBB',
            nameProgram: 'BBBBBB',
            initial: 'BBBBBB',
            stateProgram: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);
        service
          .update(expected)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should return a list of Program', () => {
        const returnedFromService = Object.assign(
          {
            codeProgram: 'BBBBBB',
            version: 'BBBBBB',
            nameProgram: 'BBBBBB',
            initial: 'BBBBBB',
            stateProgram: 'BBBBBB'
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .query(expected)
          .pipe(
            take(1),
            map(resp => resp.body)
          )
          .subscribe(body => (expectedResult = body));
        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Program', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
