import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SkyfallTestModule } from '../../../test.module';
import { ResultSeenDetailComponent } from 'app/entities/result-seen/result-seen-detail.component';
import { ResultSeen } from 'app/shared/model/result-seen.model';

describe('Component Tests', () => {
  describe('ResultSeen Management Detail Component', () => {
    let comp: ResultSeenDetailComponent;
    let fixture: ComponentFixture<ResultSeenDetailComponent>;
    const route = ({ data: of({ resultSeen: new ResultSeen(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SkyfallTestModule],
        declarations: [ResultSeenDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ResultSeenDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ResultSeenDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.resultSeen).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
