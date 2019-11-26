import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { SkyfallTestModule } from '../../../test.module';
import { AmbientDeleteDialogComponent } from 'app/entities/ambient/ambient-delete-dialog.component';
import { AmbientService } from 'app/entities/ambient/ambient.service';

describe('Component Tests', () => {
  describe('Ambient Management Delete Component', () => {
    let comp: AmbientDeleteDialogComponent;
    let fixture: ComponentFixture<AmbientDeleteDialogComponent>;
    let service: AmbientService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SkyfallTestModule],
        declarations: [AmbientDeleteDialogComponent]
      })
        .overrideTemplate(AmbientDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(AmbientDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AmbientService);
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
