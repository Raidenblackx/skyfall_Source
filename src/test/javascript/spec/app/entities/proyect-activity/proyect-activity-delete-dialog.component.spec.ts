import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { SkyfallTestModule } from '../../../test.module';
import { ProyectActivityDeleteDialogComponent } from 'app/entities/proyect-activity/proyect-activity-delete-dialog.component';
import { ProyectActivityService } from 'app/entities/proyect-activity/proyect-activity.service';

describe('Component Tests', () => {
  describe('ProyectActivity Management Delete Component', () => {
    let comp: ProyectActivityDeleteDialogComponent;
    let fixture: ComponentFixture<ProyectActivityDeleteDialogComponent>;
    let service: ProyectActivityService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SkyfallTestModule],
        declarations: [ProyectActivityDeleteDialogComponent]
      })
        .overrideTemplate(ProyectActivityDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProyectActivityDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProyectActivityService);
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
