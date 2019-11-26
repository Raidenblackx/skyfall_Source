import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { SkyfallTestModule } from '../../../test.module';
import { LevelFormationDeleteDialogComponent } from 'app/entities/level-formation/level-formation-delete-dialog.component';
import { LevelFormationService } from 'app/entities/level-formation/level-formation.service';

describe('Component Tests', () => {
  describe('LevelFormation Management Delete Component', () => {
    let comp: LevelFormationDeleteDialogComponent;
    let fixture: ComponentFixture<LevelFormationDeleteDialogComponent>;
    let service: LevelFormationService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SkyfallTestModule],
        declarations: [LevelFormationDeleteDialogComponent]
      })
        .overrideTemplate(LevelFormationDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(LevelFormationDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(LevelFormationService);
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
