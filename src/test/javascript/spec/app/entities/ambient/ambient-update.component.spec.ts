import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SkyfallTestModule } from '../../../test.module';
import { AmbientUpdateComponent } from 'app/entities/ambient/ambient-update.component';
import { AmbientService } from 'app/entities/ambient/ambient.service';
import { Ambient } from 'app/shared/model/ambient.model';

describe('Component Tests', () => {
  describe('Ambient Management Update Component', () => {
    let comp: AmbientUpdateComponent;
    let fixture: ComponentFixture<AmbientUpdateComponent>;
    let service: AmbientService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SkyfallTestModule],
        declarations: [AmbientUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(AmbientUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(AmbientUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AmbientService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Ambient(123);
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
        const entity = new Ambient();
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
