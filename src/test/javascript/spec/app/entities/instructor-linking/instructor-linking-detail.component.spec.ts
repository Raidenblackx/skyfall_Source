import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SkyfallTestModule } from '../../../test.module';
import { InstructorLinkingDetailComponent } from 'app/entities/instructor-linking/instructor-linking-detail.component';
import { InstructorLinking } from 'app/shared/model/instructor-linking.model';

describe('Component Tests', () => {
  describe('InstructorLinking Management Detail Component', () => {
    let comp: InstructorLinkingDetailComponent;
    let fixture: ComponentFixture<InstructorLinkingDetailComponent>;
    const route = ({ data: of({ instructorLinking: new InstructorLinking(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SkyfallTestModule],
        declarations: [InstructorLinkingDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(InstructorLinkingDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(InstructorLinkingDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.instructorLinking).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
