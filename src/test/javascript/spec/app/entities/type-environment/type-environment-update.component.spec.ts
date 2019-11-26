import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SkyfallTestModule } from '../../../test.module';
import { TypeEnvironmentUpdateComponent } from 'app/entities/type-environment/type-environment-update.component';
import { TypeEnvironmentService } from 'app/entities/type-environment/type-environment.service';
import { TypeEnvironment } from 'app/shared/model/type-environment.model';

describe('Component Tests', () => {
  describe('TypeEnvironment Management Update Component', () => {
    let comp: TypeEnvironmentUpdateComponent;
    let fixture: ComponentFixture<TypeEnvironmentUpdateComponent>;
    let service: TypeEnvironmentService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SkyfallTestModule],
        declarations: [TypeEnvironmentUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(TypeEnvironmentUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TypeEnvironmentUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TypeEnvironmentService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new TypeEnvironment(123);
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
        const entity = new TypeEnvironment();
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
