import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SkyfallTestModule } from '../../../test.module';
import { PlanningActivityUpdateComponent } from 'app/entities/planning-activity/planning-activity-update.component';
import { PlanningActivityService } from 'app/entities/planning-activity/planning-activity.service';
import { PlanningActivity } from 'app/shared/model/planning-activity.model';

describe('Component Tests', () => {
  describe('PlanningActivity Management Update Component', () => {
    let comp: PlanningActivityUpdateComponent;
    let fixture: ComponentFixture<PlanningActivityUpdateComponent>;
    let service: PlanningActivityService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SkyfallTestModule],
        declarations: [PlanningActivityUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(PlanningActivityUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PlanningActivityUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PlanningActivityService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new PlanningActivity(123);
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
        const entity = new PlanningActivity();
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
