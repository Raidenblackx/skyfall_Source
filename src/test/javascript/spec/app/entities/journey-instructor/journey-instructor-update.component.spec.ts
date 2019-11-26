import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SkyfallTestModule } from '../../../test.module';
import { JourneyInstructorUpdateComponent } from 'app/entities/journey-instructor/journey-instructor-update.component';
import { JourneyInstructorService } from 'app/entities/journey-instructor/journey-instructor.service';
import { JourneyInstructor } from 'app/shared/model/journey-instructor.model';

describe('Component Tests', () => {
  describe('JourneyInstructor Management Update Component', () => {
    let comp: JourneyInstructorUpdateComponent;
    let fixture: ComponentFixture<JourneyInstructorUpdateComponent>;
    let service: JourneyInstructorService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SkyfallTestModule],
        declarations: [JourneyInstructorUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(JourneyInstructorUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(JourneyInstructorUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(JourneyInstructorService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new JourneyInstructor(123);
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
        const entity = new JourneyInstructor();
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
