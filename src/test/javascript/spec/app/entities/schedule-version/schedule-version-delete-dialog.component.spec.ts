import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { SkyfallTestModule } from '../../../test.module';
import { ScheduleVersionDeleteDialogComponent } from 'app/entities/schedule-version/schedule-version-delete-dialog.component';
import { ScheduleVersionService } from 'app/entities/schedule-version/schedule-version.service';

describe('Component Tests', () => {
  describe('ScheduleVersion Management Delete Component', () => {
    let comp: ScheduleVersionDeleteDialogComponent;
    let fixture: ComponentFixture<ScheduleVersionDeleteDialogComponent>;
    let service: ScheduleVersionService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SkyfallTestModule],
        declarations: [ScheduleVersionDeleteDialogComponent]
      })
        .overrideTemplate(ScheduleVersionDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ScheduleVersionDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ScheduleVersionService);
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
