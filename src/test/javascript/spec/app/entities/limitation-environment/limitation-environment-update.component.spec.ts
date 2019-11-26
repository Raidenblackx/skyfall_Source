import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SkyfallTestModule } from '../../../test.module';
import { LimitationEnvironmentUpdateComponent } from 'app/entities/limitation-environment/limitation-environment-update.component';
import { LimitationEnvironmentService } from 'app/entities/limitation-environment/limitation-environment.service';
import { LimitationEnvironment } from 'app/shared/model/limitation-environment.model';

describe('Component Tests', () => {
  describe('LimitationEnvironment Management Update Component', () => {
    let comp: LimitationEnvironmentUpdateComponent;
    let fixture: ComponentFixture<LimitationEnvironmentUpdateComponent>;
    let service: LimitationEnvironmentService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SkyfallTestModule],
        declarations: [LimitationEnvironmentUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(LimitationEnvironmentUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(LimitationEnvironmentUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(LimitationEnvironmentService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new LimitationEnvironment(123);
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
        const entity = new LimitationEnvironment();
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
