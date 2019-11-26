import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { SkyfallTestModule } from '../../../test.module';
import { LearningResultDeleteDialogComponent } from 'app/entities/learning-result/learning-result-delete-dialog.component';
import { LearningResultService } from 'app/entities/learning-result/learning-result.service';

describe('Component Tests', () => {
  describe('LearningResult Management Delete Component', () => {
    let comp: LearningResultDeleteDialogComponent;
    let fixture: ComponentFixture<LearningResultDeleteDialogComponent>;
    let service: LearningResultService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SkyfallTestModule],
        declarations: [LearningResultDeleteDialogComponent]
      })
        .overrideTemplate(LearningResultDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(LearningResultDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(LearningResultService);
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
