import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SkyfallTestModule } from '../../../test.module';
import { TrimesterUpdateComponent } from 'app/entities/trimester/trimester-update.component';
import { TrimesterService } from 'app/entities/trimester/trimester.service';
import { Trimester } from 'app/shared/model/trimester.model';

describe('Component Tests', () => {
  describe('Trimester Management Update Component', () => {
    let comp: TrimesterUpdateComponent;
    let fixture: ComponentFixture<TrimesterUpdateComponent>;
    let service: TrimesterService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SkyfallTestModule],
        declarations: [TrimesterUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(TrimesterUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TrimesterUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TrimesterService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Trimester(123);
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
        const entity = new Trimester();
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
