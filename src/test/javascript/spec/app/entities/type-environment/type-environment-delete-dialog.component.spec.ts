import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { SkyfallTestModule } from '../../../test.module';
import { TypeEnvironmentDeleteDialogComponent } from 'app/entities/type-environment/type-environment-delete-dialog.component';
import { TypeEnvironmentService } from 'app/entities/type-environment/type-environment.service';

describe('Component Tests', () => {
  describe('TypeEnvironment Management Delete Component', () => {
    let comp: TypeEnvironmentDeleteDialogComponent;
    let fixture: ComponentFixture<TypeEnvironmentDeleteDialogComponent>;
    let service: TypeEnvironmentService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SkyfallTestModule],
        declarations: [TypeEnvironmentDeleteDialogComponent]
      })
        .overrideTemplate(TypeEnvironmentDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TypeEnvironmentDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TypeEnvironmentService);
      mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
      mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));
    });
  });
});
