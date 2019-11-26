import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SkyfallTestModule } from '../../../test.module';
import { ScheduleAvailabilityUpdateComponent } from 'app/entities/schedule-availability/schedule-availability-update.component';
import { ScheduleAvailabilityService } from 'app/entities/schedule-availability/schedule-availability.service';
import { ScheduleAvailability } from 'app/shared/model/schedule-availability.model';

describe('Component Tests', () => {
  describe('ScheduleAvailability Management Update Component', () => {
    let comp: ScheduleAvailabilityUpdateComponent;
    let fixture: ComponentFixture<ScheduleAvailabilityUpdateComponent>;
    let service: ScheduleAvailabilityService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SkyfallTestModule],
        declarations: [ScheduleAvailabilityUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ScheduleAvailabilityUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ScheduleAvailabilityUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ScheduleAvailabilityService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ScheduleAvailability(123);
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
        const entity = new ScheduleAvailability();
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
