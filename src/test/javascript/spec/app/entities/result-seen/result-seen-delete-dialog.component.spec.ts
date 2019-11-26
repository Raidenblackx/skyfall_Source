import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { SkyfallTestModule } from '../../../test.module';
import { ResultSeenDeleteDialogComponent } from 'app/entities/result-seen/result-seen-delete-dialog.component';
import { ResultSeenService } from 'app/entities/result-seen/result-seen.service';

describe('Component Tests', () => {
  describe('ResultSeen Management Delete Component', () => {
    let comp: ResultSeenDeleteDialogComponent;
    let fixture: ComponentFixture<ResultSeenDeleteDialogComponent>;
    let service: ResultSeenService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SkyfallTestModule],
        declarations: [ResultSeenDeleteDialogComponent]
      })
        .overrideTemplate(ResultSeenDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ResultSeenDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ResultSeenService);
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
