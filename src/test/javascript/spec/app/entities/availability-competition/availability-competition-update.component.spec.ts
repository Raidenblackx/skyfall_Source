import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SkyfallTestModule } from '../../../test.module';
import { AvailabilityCompetitionUpdateComponent } from 'app/entities/availability-competition/availability-competition-update.component';
import { AvailabilityCompetitionService } from 'app/entities/availability-competition/availability-competition.service';
import { AvailabilityCompetition } from 'app/shared/model/availability-competition.model';

describe('Component Tests', () => {
  describe('AvailabilityCompetition Management Update Component', () => {
    let comp: AvailabilityCompetitionUpdateComponent;
    let fixture: ComponentFixture<AvailabilityCompetitionUpdateComponent>;
    let service: AvailabilityCompetitionService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SkyfallTestModule],
        declarations: [AvailabilityCompetitionUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(AvailabilityCompetitionUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(AvailabilityCompetitionUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AvailabilityCompetitionService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new AvailabilityCompetition(123);
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
        const entity = new AvailabilityCompetition();
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
