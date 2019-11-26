import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SkyfallTestModule } from '../../../test.module';
import { CurrentQuarterUpdateComponent } from 'app/entities/current-quarter/current-quarter-update.component';
import { CurrentQuarterService } from 'app/entities/current-quarter/current-quarter.service';
import { CurrentQuarter } from 'app/shared/model/current-quarter.model';

describe('Component Tests', () => {
  describe('CurrentQuarter Management Update Component', () => {
    let comp: CurrentQuarterUpdateComponent;
    let fixture: ComponentFixture<CurrentQuarterUpdateComponent>;
    let service: CurrentQuarterService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SkyfallTestModule],
        declarations: [CurrentQuarterUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(CurrentQuarterUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CurrentQuarterUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CurrentQuarterService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new CurrentQuarter(123);
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
        const entity = new CurrentQuarter();
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
