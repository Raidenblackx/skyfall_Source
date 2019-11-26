import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SkyfallTestModule } from '../../../test.module';
import { TimeStudyDetailComponent } from 'app/entities/time-study/time-study-detail.component';
import { TimeStudy } from 'app/shared/model/time-study.model';

describe('Component Tests', () => {
  describe('TimeStudy Management Detail Component', () => {
    let comp: TimeStudyDetailComponent;
    let fixture: ComponentFixture<TimeStudyDetailComponent>;
    const route = ({ data: of({ timeStudy: new TimeStudy(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SkyfallTestModule],
        declarations: [TimeStudyDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(TimeStudyDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TimeStudyDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.timeStudy).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
