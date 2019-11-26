import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SkyfallTestModule } from '../../../test.module';
import { LimitationEnvironmentDetailComponent } from 'app/entities/limitation-environment/limitation-environment-detail.component';
import { LimitationEnvironment } from 'app/shared/model/limitation-environment.model';

describe('Component Tests', () => {
  describe('LimitationEnvironment Management Detail Component', () => {
    let comp: LimitationEnvironmentDetailComponent;
    let fixture: ComponentFixture<LimitationEnvironmentDetailComponent>;
    const route = ({ data: of({ limitationEnvironment: new LimitationEnvironment(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SkyfallTestModule],
        declarations: [LimitationEnvironmentDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(LimitationEnvironmentDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(LimitationEnvironmentDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.limitationEnvironment).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
