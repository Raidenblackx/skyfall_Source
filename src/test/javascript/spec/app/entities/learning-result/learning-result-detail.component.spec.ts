import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SkyfallTestModule } from '../../../test.module';
import { LearningResultDetailComponent } from 'app/entities/learning-result/learning-result-detail.component';
import { LearningResult } from 'app/shared/model/learning-result.model';

describe('Component Tests', () => {
  describe('LearningResult Management Detail Component', () => {
    let comp: LearningResultDetailComponent;
    let fixture: ComponentFixture<LearningResultDetailComponent>;
    const route = ({ data: of({ learningResult: new LearningResult(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SkyfallTestModule],
        declarations: [LearningResultDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(LearningResultDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(LearningResultDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.learningResult).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
