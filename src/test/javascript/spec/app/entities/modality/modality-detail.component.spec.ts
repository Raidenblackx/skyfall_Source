import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SkyfallTestModule } from '../../../test.module';
import { ModalityDetailComponent } from 'app/entities/modality/modality-detail.component';
import { Modality } from 'app/shared/model/modality.model';

describe('Component Tests', () => {
  describe('Modality Management Detail Component', () => {
    let comp: ModalityDetailComponent;
    let fixture: ComponentFixture<ModalityDetailComponent>;
    const route = ({ data: of({ modality: new Modality(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SkyfallTestModule],
        declarations: [ModalityDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ModalityDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ModalityDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.modality).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
