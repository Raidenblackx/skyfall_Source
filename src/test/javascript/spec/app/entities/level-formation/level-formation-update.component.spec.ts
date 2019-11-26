import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SkyfallTestModule } from '../../../test.module';
import { LevelFormationUpdateComponent } from 'app/entities/level-formation/level-formation-update.component';
import { LevelFormationService } from 'app/entities/level-formation/level-formation.service';
import { LevelFormation } from 'app/shared/model/level-formation.model';

describe('Component Tests', () => {
  describe('LevelFormation Management Update Component', () => {
    let comp: LevelFormationUpdateComponent;
    let fixture: ComponentFixture<LevelFormationUpdateComponent>;
    let service: LevelFormationService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SkyfallTestModule],
        declarations: [LevelFormationUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(LevelFormationUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(LevelFormationUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(LevelFormationService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new LevelFormation(123);
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
        const entity = new LevelFormation();
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
