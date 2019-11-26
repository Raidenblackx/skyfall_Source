import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SkyfallTestModule } from '../../../test.module';
import { TrimesterPlanningUpdateComponent } from 'app/entities/trimester-planning/trimester-planning-update.component';
import { TrimesterPlanningService } from 'app/entities/trimester-planning/trimester-planning.service';
import { TrimesterPlanning } from 'app/shared/model/trimester-planning.model';

describe('Component Tests', () => {
  describe('TrimesterPlanning Management Update Component', () => {
    let comp: TrimesterPlanningUpdateComponent;
    let fixture: ComponentFixture<TrimesterPlanningUpdateComponent>;
    let service: TrimesterPlanningService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SkyfallTestModule],
        declarations: [TrimesterPlanningUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(TrimesterPlanningUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TrimesterPlanningUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TrimesterPlanningService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new TrimesterPlanning(123);
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
        const entity = new TrimesterPlanning();
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
