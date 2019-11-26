import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SkyfallTestModule } from '../../../test.module';
import { LevelFormationDetailComponent } from 'app/entities/level-formation/level-formation-detail.component';
import { LevelFormation } from 'app/shared/model/level-formation.model';

describe('Component Tests', () => {
  describe('LevelFormation Management Detail Component', () => {
    let comp: LevelFormationDetailComponent;
    let fixture: ComponentFixture<LevelFormationDetailComponent>;
    const route = ({ data: of({ levelFormation: new LevelFormation(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SkyfallTestModule],
        declarations: [LevelFormationDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(LevelFormationDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(LevelFormationDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.levelFormation).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
