import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SkyfallTestModule } from '../../../test.module';
import { InstructorAreaDetailComponent } from 'app/entities/instructor-area/instructor-area-detail.component';
import { InstructorArea } from 'app/shared/model/instructor-area.model';

describe('Component Tests', () => {
  describe('InstructorArea Management Detail Component', () => {
    let comp: InstructorAreaDetailComponent;
    let fixture: ComponentFixture<InstructorAreaDetailComponent>;
    const route = ({ data: of({ instructorArea: new InstructorArea(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SkyfallTestModule],
        declarations: [InstructorAreaDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(InstructorAreaDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(InstructorAreaDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.instructorArea).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
