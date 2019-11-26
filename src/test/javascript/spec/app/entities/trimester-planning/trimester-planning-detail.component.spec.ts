import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SkyfallTestModule } from '../../../test.module';
import { TrimesterPlanningDetailComponent } from 'app/entities/trimester-planning/trimester-planning-detail.component';
import { TrimesterPlanning } from 'app/shared/model/trimester-planning.model';

describe('Component Tests', () => {
  describe('TrimesterPlanning Management Detail Component', () => {
    let comp: TrimesterPlanningDetailComponent;
    let fixture: ComponentFixture<TrimesterPlanningDetailComponent>;
    const route = ({ data: of({ trimesterPlanning: new TrimesterPlanning(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SkyfallTestModule],
        declarations: [TrimesterPlanningDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(TrimesterPlanningDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TrimesterPlanningDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.trimesterPlanning).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
