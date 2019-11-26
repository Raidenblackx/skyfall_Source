import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { SkyfallTestModule } from '../../../test.module';
import { JourneyInstructorDeleteDialogComponent } from 'app/entities/journey-instructor/journey-instructor-delete-dialog.component';
import { JourneyInstructorService } from 'app/entities/journey-instructor/journey-instructor.service';

describe('Component Tests', () => {
  describe('JourneyInstructor Management Delete Component', () => {
    let comp: JourneyInstructorDeleteDialogComponent;
    let fixture: ComponentFixture<JourneyInstructorDeleteDialogComponent>;
    let service: JourneyInstructorService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SkyfallTestModule],
        declarations: [JourneyInstructorDeleteDialogComponent]
      })
        .overrideTemplate(JourneyInstructorDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(JourneyInstructorDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(JourneyInstructorService);
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
