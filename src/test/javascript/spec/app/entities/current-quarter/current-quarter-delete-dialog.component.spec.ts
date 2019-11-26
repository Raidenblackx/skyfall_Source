import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { SkyfallTestModule } from '../../../test.module';
import { CurrentQuarterDeleteDialogComponent } from 'app/entities/current-quarter/current-quarter-delete-dialog.component';
import { CurrentQuarterService } from 'app/entities/current-quarter/current-quarter.service';

describe('Component Tests', () => {
  describe('CurrentQuarter Management Delete Component', () => {
    let comp: CurrentQuarterDeleteDialogComponent;
    let fixture: ComponentFixture<CurrentQuarterDeleteDialogComponent>;
    let service: CurrentQuarterService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SkyfallTestModule],
        declarations: [CurrentQuarterDeleteDialogComponent]
      })
        .overrideTemplate(CurrentQuarterDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CurrentQuarterDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CurrentQuarterService);
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
