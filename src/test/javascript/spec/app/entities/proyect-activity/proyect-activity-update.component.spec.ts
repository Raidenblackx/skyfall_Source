import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SkyfallTestModule } from '../../../test.module';
import { ProyectActivityUpdateComponent } from 'app/entities/proyect-activity/proyect-activity-update.component';
import { ProyectActivityService } from 'app/entities/proyect-activity/proyect-activity.service';
import { ProyectActivity } from 'app/shared/model/proyect-activity.model';

describe('Component Tests', () => {
  describe('ProyectActivity Management Update Component', () => {
    let comp: ProyectActivityUpdateComponent;
    let fixture: ComponentFixture<ProyectActivityUpdateComponent>;
    let service: ProyectActivityService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SkyfallTestModule],
        declarations: [ProyectActivityUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ProyectActivityUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProyectActivityUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProyectActivityService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ProyectActivity(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new ProyectActivity();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
