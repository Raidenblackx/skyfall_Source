import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SkyfallTestModule } from '../../../test.module';
import { PlanningActivityDetailComponent } from 'app/entities/planning-activity/planning-activity-detail.component';
import { PlanningActivity } from 'app/shared/model/planning-activity.model';

describe('Component Tests', () => {
  describe('PlanningActivity Management Detail Component', () => {
    let comp: PlanningActivityDetailComponent;
    let fixture: ComponentFixture<PlanningActivityDetailComponent>;
    const route = ({ data: of({ planningActivity: new PlanningActivity(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SkyfallTestModule],
        declarations: [PlanningActivityDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(PlanningActivityDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PlanningActivityDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.planningActivity).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
