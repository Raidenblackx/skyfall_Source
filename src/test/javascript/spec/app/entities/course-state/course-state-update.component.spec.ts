import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SkyfallTestModule } from '../../../test.module';
import { CourseStateUpdateComponent } from 'app/entities/course-state/course-state-update.component';
import { CourseStateService } from 'app/entities/course-state/course-state.service';
import { CourseState } from 'app/shared/model/course-state.model';

describe('Component Tests', () => {
  describe('CourseState Management Update Component', () => {
    let comp: CourseStateUpdateComponent;
    let fixture: ComponentFixture<CourseStateUpdateComponent>;
    let service: CourseStateService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SkyfallTestModule],
        declarations: [CourseStateUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(CourseStateUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CourseStateUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CourseStateService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new CourseState(123);
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
        const entity = new CourseState();
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
