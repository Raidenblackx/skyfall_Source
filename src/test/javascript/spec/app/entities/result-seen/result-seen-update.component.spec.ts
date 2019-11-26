import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SkyfallTestModule } from '../../../test.module';
import { ResultSeenUpdateComponent } from 'app/entities/result-seen/result-seen-update.component';
import { ResultSeenService } from 'app/entities/result-seen/result-seen.service';
import { ResultSeen } from 'app/shared/model/result-seen.model';

describe('Component Tests', () => {
  describe('ResultSeen Management Update Component', () => {
    let comp: ResultSeenUpdateComponent;
    let fixture: ComponentFixture<ResultSeenUpdateComponent>;
    let service: ResultSeenService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SkyfallTestModule],
        declarations: [ResultSeenUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ResultSeenUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ResultSeenUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ResultSeenService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ResultSeen(123);
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
        const entity = new ResultSeen();
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
