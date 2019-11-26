import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SkyfallTestModule } from '../../../test.module';
import { JourneyInstructorDetailComponent } from 'app/entities/journey-instructor/journey-instructor-detail.component';
import { JourneyInstructor } from 'app/shared/model/journey-instructor.model';

describe('Component Tests', () => {
  describe('JourneyInstructor Management Detail Component', () => {
    let comp: JourneyInstructorDetailComponent;
    let fixture: ComponentFixture<JourneyInstructorDetailComponent>;
    const route = ({ data: of({ journeyInstructor: new JourneyInstructor(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SkyfallTestModule],
        declarations: [JourneyInstructorDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(JourneyInstructorDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(JourneyInstructorDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.journeyInstructor).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
