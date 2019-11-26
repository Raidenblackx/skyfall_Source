import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { SkyfallTestModule } from '../../../test.module';
import { WorkingDayDeleteDialogComponent } from 'app/entities/working-day/working-day-delete-dialog.component';
import { WorkingDayService } from 'app/entities/working-day/working-day.service';

describe('Component Tests', () => {
  describe('WorkingDay Management Delete Component', () => {
    let comp: WorkingDayDeleteDialogComponent;
    let fixture: ComponentFixture<WorkingDayDeleteDialogComponent>;
    let service: WorkingDayService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SkyfallTestModule],
        declarations: [WorkingDayDeleteDialogComponent]
      })
        .overrideTemplate(WorkingDayDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(WorkingDayDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(WorkingDayService);
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
