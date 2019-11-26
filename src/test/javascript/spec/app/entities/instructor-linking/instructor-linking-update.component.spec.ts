import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SkyfallTestModule } from '../../../test.module';
import { InstructorLinkingUpdateComponent } from 'app/entities/instructor-linking/instructor-linking-update.component';
import { InstructorLinkingService } from 'app/entities/instructor-linking/instructor-linking.service';
import { InstructorLinking } from 'app/shared/model/instructor-linking.model';

describe('Component Tests', () => {
  describe('InstructorLinking Management Update Component', () => {
    let comp: InstructorLinkingUpdateComponent;
    let fixture: ComponentFixture<InstructorLinkingUpdateComponent>;
    let service: InstructorLinkingService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SkyfallTestModule],
        declarations: [InstructorLinkingUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(InstructorLinkingUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(InstructorLinkingUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(InstructorLinkingService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new InstructorLinking(123);
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
        const entity = new InstructorLinking();
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
