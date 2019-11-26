import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SkyfallTestModule } from '../../../test.module';
import { InstructorAreaUpdateComponent } from 'app/entities/instructor-area/instructor-area-update.component';
import { InstructorAreaService } from 'app/entities/instructor-area/instructor-area.service';
import { InstructorArea } from 'app/shared/model/instructor-area.model';

describe('Component Tests', () => {
  describe('InstructorArea Management Update Component', () => {
    let comp: InstructorAreaUpdateComponent;
    let fixture: ComponentFixture<InstructorAreaUpdateComponent>;
    let service: InstructorAreaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SkyfallTestModule],
        declarations: [InstructorAreaUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(InstructorAreaUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(InstructorAreaUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(InstructorAreaService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new InstructorArea(123);
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
        const entity = new InstructorArea();
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
