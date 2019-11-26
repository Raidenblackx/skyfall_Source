import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SkyfallTestModule } from '../../../test.module';
import { TypeEnvironmentDetailComponent } from 'app/entities/type-environment/type-environment-detail.component';
import { TypeEnvironment } from 'app/shared/model/type-environment.model';

describe('Component Tests', () => {
  describe('TypeEnvironment Management Detail Component', () => {
    let comp: TypeEnvironmentDetailComponent;
    let fixture: ComponentFixture<TypeEnvironmentDetailComponent>;
    const route = ({ data: of({ typeEnvironment: new TypeEnvironment(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SkyfallTestModule],
        declarations: [TypeEnvironmentDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(TypeEnvironmentDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TypeEnvironmentDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.typeEnvironment).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
