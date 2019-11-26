import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { SkyfallTestModule } from '../../../test.module';
import { LinkageDeleteDialogComponent } from 'app/entities/linkage/linkage-delete-dialog.component';
import { LinkageService } from 'app/entities/linkage/linkage.service';

describe('Component Tests', () => {
  describe('Linkage Management Delete Component', () => {
    let comp: LinkageDeleteDialogComponent;
    let fixture: ComponentFixture<LinkageDeleteDialogComponent>;
    let service: LinkageService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SkyfallTestModule],
        declarations: [LinkageDeleteDialogComponent]
      })
        .overrideTemplate(LinkageDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(LinkageDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(LinkageService);
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
