import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { SkyfallTestModule } from '../../../test.module';
import { TrimesterDeleteDialogComponent } from 'app/entities/trimester/trimester-delete-dialog.component';
import { TrimesterService } from 'app/entities/trimester/trimester.service';

describe('Component Tests', () => {
  describe('Trimester Management Delete Component', () => {
    let comp: TrimesterDeleteDialogComponent;
    let fixture: ComponentFixture<TrimesterDeleteDialogComponent>;
    let service: TrimesterService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SkyfallTestModule],
        declarations: [TrimesterDeleteDialogComponent]
      })
        .overrideTemplate(TrimesterDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TrimesterDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TrimesterService);
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
