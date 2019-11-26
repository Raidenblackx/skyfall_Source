import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { SkyfallTestModule } from '../../../test.module';
import { CourseStateDeleteDialogComponent } from 'app/entities/course-state/course-state-delete-dialog.component';
import { CourseStateService } from 'app/entities/course-state/course-state.service';

describe('Component Tests', () => {
  describe('CourseState Management Delete Component', () => {
    let comp: CourseStateDeleteDialogComponent;
    let fixture: ComponentFixture<CourseStateDeleteDialogComponent>;
    let service: CourseStateService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SkyfallTestModule],
        declarations: [CourseStateDeleteDialogComponent]
      })
        .overrideTemplate(CourseStateDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CourseStateDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CourseStateService);
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
