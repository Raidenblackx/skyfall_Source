import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { SkyfallTestModule } from '../../../test.module';
import { TrimesterPlanningDeleteDialogComponent } from 'app/entities/trimester-planning/trimester-planning-delete-dialog.component';
import { TrimesterPlanningService } from 'app/entities/trimester-planning/trimester-planning.service';

describe('Component Tests', () => {
  describe('TrimesterPlanning Management Delete Component', () => {
    let comp: TrimesterPlanningDeleteDialogComponent;
    let fixture: ComponentFixture<TrimesterPlanningDeleteDialogComponent>;
    let service: TrimesterPlanningService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SkyfallTestModule],
        declarations: [TrimesterPlanningDeleteDialogComponent]
      })
        .overrideTemplate(TrimesterPlanningDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TrimesterPlanningDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TrimesterPlanningService);
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
