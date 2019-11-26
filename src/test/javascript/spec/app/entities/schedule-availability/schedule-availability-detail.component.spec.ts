import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SkyfallTestModule } from '../../../test.module';
import { ScheduleAvailabilityDetailComponent } from 'app/entities/schedule-availability/schedule-availability-detail.component';
import { ScheduleAvailability } from 'app/shared/model/schedule-availability.model';

describe('Component Tests', () => {
  describe('ScheduleAvailability Management Detail Component', () => {
    let comp: ScheduleAvailabilityDetailComponent;
    let fixture: ComponentFixture<ScheduleAvailabilityDetailComponent>;
    const route = ({ data: of({ scheduleAvailability: new ScheduleAvailability(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SkyfallTestModule],
        declarations: [ScheduleAvailabilityDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ScheduleAvailabilityDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ScheduleAvailabilityDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.scheduleAvailability).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
