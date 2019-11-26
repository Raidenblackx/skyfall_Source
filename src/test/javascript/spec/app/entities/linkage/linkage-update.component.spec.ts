import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SkyfallTestModule } from '../../../test.module';
import { LinkageUpdateComponent } from 'app/entities/linkage/linkage-update.component';
import { LinkageService } from 'app/entities/linkage/linkage.service';
import { Linkage } from 'app/shared/model/linkage.model';

describe('Component Tests', () => {
  describe('Linkage Management Update Component', () => {
    let comp: LinkageUpdateComponent;
    let fixture: ComponentFixture<LinkageUpdateComponent>;
    let service: LinkageService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SkyfallTestModule],
        declarations: [LinkageUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(LinkageUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(LinkageUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(LinkageService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Linkage(123);
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
        const entity = new Linkage();
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
