import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SkyfallTestModule } from '../../../test.module';
import { LearningResultUpdateComponent } from 'app/entities/learning-result/learning-result-update.component';
import { LearningResultService } from 'app/entities/learning-result/learning-result.service';
import { LearningResult } from 'app/shared/model/learning-result.model';

describe('Component Tests', () => {
  describe('LearningResult Management Update Component', () => {
    let comp: LearningResultUpdateComponent;
    let fixture: ComponentFixture<LearningResultUpdateComponent>;
    let service: LearningResultService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SkyfallTestModule],
        declarations: [LearningResultUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(LearningResultUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(LearningResultUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(LearningResultService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new LearningResult(123);
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
        const entity = new LearningResult();
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
