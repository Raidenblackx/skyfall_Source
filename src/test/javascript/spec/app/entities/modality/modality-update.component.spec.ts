import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SkyfallTestModule } from '../../../test.module';
import { ModalityUpdateComponent } from 'app/entities/modality/modality-update.component';
import { ModalityService } from 'app/entities/modality/modality.service';
import { Modality } from 'app/shared/model/modality.model';

describe('Component Tests', () => {
  describe('Modality Management Update Component', () => {
    let comp: ModalityUpdateComponent;
    let fixture: ComponentFixture<ModalityUpdateComponent>;
    let service: ModalityService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SkyfallTestModule],
        declarations: [ModalityUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ModalityUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ModalityUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ModalityService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Modality(123);
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
        const entity = new Modality();
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
