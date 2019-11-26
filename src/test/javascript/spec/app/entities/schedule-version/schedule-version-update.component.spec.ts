import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SkyfallTestModule } from '../../../test.module';
import { ScheduleVersionUpdateComponent } from 'app/entities/schedule-version/schedule-version-update.component';
import { ScheduleVersionService } from 'app/entities/schedule-version/schedule-version.service';
import { ScheduleVersion } from 'app/shared/model/schedule-version.model';

describe('Component Tests', () => {
  describe('ScheduleVersion Management Update Component', () => {
    let comp: ScheduleVersionUpdateComponent;
    let fixture: ComponentFixture<ScheduleVersionUpdateComponent>;
    let service: ScheduleVersionService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SkyfallTestModule],
        declarations: [ScheduleVersionUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ScheduleVersionUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ScheduleVersionUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ScheduleVersionService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ScheduleVersion(123);
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
        const entity = new ScheduleVersion();
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
