import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SkyfallTestModule } from '../../../test.module';
import { WorkingDayDetailComponent } from 'app/entities/working-day/working-day-detail.component';
import { WorkingDay } from 'app/shared/model/working-day.model';

describe('Component Tests', () => {
  describe('WorkingDay Management Detail Component', () => {
    let comp: WorkingDayDetailComponent;
    let fixture: ComponentFixture<WorkingDayDetailComponent>;
    const route = ({ data: of({ workingDay: new WorkingDay(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SkyfallTestModule],
        declarations: [WorkingDayDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(WorkingDayDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(WorkingDayDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.workingDay).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
