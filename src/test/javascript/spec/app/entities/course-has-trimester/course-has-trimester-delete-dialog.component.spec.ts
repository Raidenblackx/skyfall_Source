import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { SkyfallTestModule } from '../../../test.module';
import { CourseHasTrimesterDeleteDialogComponent } from 'app/entities/course-has-trimester/course-has-trimester-delete-dialog.component';
import { CourseHasTrimesterService } from 'app/entities/course-has-trimester/course-has-trimester.service';

describe('Component Tests', () => {
  describe('CourseHasTrimester Management Delete Component', () => {
    let comp: CourseHasTrimesterDeleteDialogComponent;
    let fixture: ComponentFixture<CourseHasTrimesterDeleteDialogComponent>;
    let service: CourseHasTrimesterService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SkyfallTestModule],
        declarations: [CourseHasTrimesterDeleteDialogComponent]
      })
        .overrideTemplate(CourseHasTrimesterDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CourseHasTrimesterDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CourseHasTrimesterService);
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
