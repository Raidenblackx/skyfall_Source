import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SkyfallTestModule } from '../../../test.module';
import { TimeStudyUpdateComponent } from 'app/entities/time-study/time-study-update.component';
import { TimeStudyService } from 'app/entities/time-study/time-study.service';
import { TimeStudy } from 'app/shared/model/time-study.model';

describe('Component Tests', () => {
  describe('TimeStudy Management Update Component', () => {
    let comp: TimeStudyUpdateComponent;
    let fixture: ComponentFixture<TimeStudyUpdateComponent>;
    let service: TimeStudyService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SkyfallTestModule],
        declarations: [TimeStudyUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(TimeStudyUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TimeStudyUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TimeStudyService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new TimeStudy(123);
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
        const entity = new TimeStudy();
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
