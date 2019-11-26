import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SkyfallTestModule } from '../../../test.module';
import { ScheduleVersionDetailComponent } from 'app/entities/schedule-version/schedule-version-detail.component';
import { ScheduleVersion } from 'app/shared/model/schedule-version.model';

describe('Component Tests', () => {
  describe('ScheduleVersion Management Detail Component', () => {
    let comp: ScheduleVersionDetailComponent;
    let fixture: ComponentFixture<ScheduleVersionDetailComponent>;
    const route = ({ data: of({ scheduleVersion: new ScheduleVersion(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SkyfallTestModule],
        declarations: [ScheduleVersionDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ScheduleVersionDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ScheduleVersionDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.scheduleVersion).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
