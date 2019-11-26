import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SkyfallTestModule } from '../../../test.module';
import { AmbientDetailComponent } from 'app/entities/ambient/ambient-detail.component';
import { Ambient } from 'app/shared/model/ambient.model';

describe('Component Tests', () => {
  describe('Ambient Management Detail Component', () => {
    let comp: AmbientDetailComponent;
    let fixture: ComponentFixture<AmbientDetailComponent>;
    const route = ({ data: of({ ambient: new Ambient(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SkyfallTestModule],
        declarations: [AmbientDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(AmbientDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(AmbientDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.ambient).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
