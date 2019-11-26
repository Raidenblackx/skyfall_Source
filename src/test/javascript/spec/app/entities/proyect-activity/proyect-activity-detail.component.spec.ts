import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SkyfallTestModule } from '../../../test.module';
import { ProyectActivityDetailComponent } from 'app/entities/proyect-activity/proyect-activity-detail.component';
import { ProyectActivity } from 'app/shared/model/proyect-activity.model';

describe('Component Tests', () => {
  describe('ProyectActivity Management Detail Component', () => {
    let comp: ProyectActivityDetailComponent;
    let fixture: ComponentFixture<ProyectActivityDetailComponent>;
    const route = ({ data: of({ proyectActivity: new ProyectActivity(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SkyfallTestModule],
        declarations: [ProyectActivityDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ProyectActivityDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProyectActivityDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.proyectActivity).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
