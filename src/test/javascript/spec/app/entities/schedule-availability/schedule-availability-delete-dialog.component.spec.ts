import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { SkyfallTestModule } from '../../../test.module';
import { ScheduleAvailabilityDeleteDialogComponent } from 'app/entities/schedule-availability/schedule-availability-delete-dialog.component';
import { ScheduleAvailabilityService } from 'app/entities/schedule-availability/schedule-availability.service';

describe('Component Tests', () => {
  describe('ScheduleAvailability Management Delete Component', () => {
    let comp: ScheduleAvailabilityDeleteDialogComponent;
    let fixture: ComponentFixture<ScheduleAvailabilityDeleteDialogComponent>;
    let service: ScheduleAvailabilityService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SkyfallTestModule],
        declarations: [ScheduleAvailabilityDeleteDialogComponent]
      })
        .overrideTemplate(ScheduleAvailabilityDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ScheduleAvailabilityDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ScheduleAvailabilityService);
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
