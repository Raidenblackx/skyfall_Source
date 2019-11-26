import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SkyfallTestModule } from '../../../test.module';
import { CourseHasTrimesterUpdateComponent } from 'app/entities/course-has-trimester/course-has-trimester-update.component';
import { CourseHasTrimesterService } from 'app/entities/course-has-trimester/course-has-trimester.service';
import { CourseHasTrimester } from 'app/shared/model/course-has-trimester.model';

describe('Component Tests', () => {
  describe('CourseHasTrimester Management Update Component', () => {
    let comp: CourseHasTrimesterUpdateComponent;
    let fixture: ComponentFixture<CourseHasTrimesterUpdateComponent>;
    let service: CourseHasTrimesterService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SkyfallTestModule],
        declarations: [CourseHasTrimesterUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(CourseHasTrimesterUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CourseHasTrimesterUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CourseHasTrimesterService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new CourseHasTrimester(123);
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
        const entity = new CourseHasTrimester();
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
