import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SkyfallTestModule } from '../../../test.module';
import { CourseHasTrimesterDetailComponent } from 'app/entities/course-has-trimester/course-has-trimester-detail.component';
import { CourseHasTrimester } from 'app/shared/model/course-has-trimester.model';

describe('Component Tests', () => {
  describe('CourseHasTrimester Management Detail Component', () => {
    let comp: CourseHasTrimesterDetailComponent;
    let fixture: ComponentFixture<CourseHasTrimesterDetailComponent>;
    const route = ({ data: of({ courseHasTrimester: new CourseHasTrimester(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SkyfallTestModule],
        declarations: [CourseHasTrimesterDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(CourseHasTrimesterDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CourseHasTrimesterDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.courseHasTrimester).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
