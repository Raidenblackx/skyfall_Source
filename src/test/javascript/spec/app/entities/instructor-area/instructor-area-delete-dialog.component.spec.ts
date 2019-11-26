import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { SkyfallTestModule } from '../../../test.module';
import { InstructorAreaDeleteDialogComponent } from 'app/entities/instructor-area/instructor-area-delete-dialog.component';
import { InstructorAreaService } from 'app/entities/instructor-area/instructor-area.service';

describe('Component Tests', () => {
  describe('InstructorArea Management Delete Component', () => {
    let comp: InstructorAreaDeleteDialogComponent;
    let fixture: ComponentFixture<InstructorAreaDeleteDialogComponent>;
    let service: InstructorAreaService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SkyfallTestModule],
        declarations: [InstructorAreaDeleteDialogComponent]
      })
        .overrideTemplate(InstructorAreaDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(InstructorAreaDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(InstructorAreaService);
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
