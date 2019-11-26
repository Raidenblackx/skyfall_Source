import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SkyfallTestModule } from '../../../test.module';
import { WorkingDayUpdateComponent } from 'app/entities/working-day/working-day-update.component';
import { WorkingDayService } from 'app/entities/working-day/working-day.service';
import { WorkingDay } from 'app/shared/model/working-day.model';

describe('Component Tests', () => {
  describe('WorkingDay Management Update Component', () => {
    let comp: WorkingDayUpdateComponent;
    let fixture: ComponentFixture<WorkingDayUpdateComponent>;
    let service: WorkingDayService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SkyfallTestModule],
        declarations: [WorkingDayUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(WorkingDayUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(WorkingDayUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(WorkingDayService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new WorkingDay(123);
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
        const entity = new WorkingDay();
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
