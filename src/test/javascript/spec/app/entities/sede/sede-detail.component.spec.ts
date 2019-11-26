import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SkyfallTestModule } from '../../../test.module';
import { SedeDetailComponent } from 'app/entities/sede/sede-detail.component';
import { Sede } from 'app/shared/model/sede.model';

describe('Component Tests', () => {
  describe('Sede Management Detail Component', () => {
    let comp: SedeDetailComponent;
    let fixture: ComponentFixture<SedeDetailComponent>;
    const route = ({ data: of({ sede: new Sede(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SkyfallTestModule],
        declarations: [SedeDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(SedeDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SedeDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.sede).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
