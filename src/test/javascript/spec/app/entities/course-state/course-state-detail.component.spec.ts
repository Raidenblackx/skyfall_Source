import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SkyfallTestModule } from '../../../test.module';
import { CourseStateDetailComponent } from 'app/entities/course-state/course-state-detail.component';
import { CourseState } from 'app/shared/model/course-state.model';

describe('Component Tests', () => {
  describe('CourseState Management Detail Component', () => {
    let comp: CourseStateDetailComponent;
    let fixture: ComponentFixture<CourseStateDetailComponent>;
    const route = ({ data: of({ courseState: new CourseState(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SkyfallTestModule],
        declarations: [CourseStateDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(CourseStateDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CourseStateDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.courseState).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
