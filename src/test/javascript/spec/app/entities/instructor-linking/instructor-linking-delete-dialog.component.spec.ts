import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { SkyfallTestModule } from '../../../test.module';
import { InstructorLinkingDeleteDialogComponent } from 'app/entities/instructor-linking/instructor-linking-delete-dialog.component';
import { InstructorLinkingService } from 'app/entities/instructor-linking/instructor-linking.service';

describe('Component Tests', () => {
  describe('InstructorLinking Management Delete Component', () => {
    let comp: InstructorLinkingDeleteDialogComponent;
    let fixture: ComponentFixture<InstructorLinkingDeleteDialogComponent>;
    let service: InstructorLinkingService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SkyfallTestModule],
        declarations: [InstructorLinkingDeleteDialogComponent]
      })
        .overrideTemplate(InstructorLinkingDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(InstructorLinkingDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(InstructorLinkingService);
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
