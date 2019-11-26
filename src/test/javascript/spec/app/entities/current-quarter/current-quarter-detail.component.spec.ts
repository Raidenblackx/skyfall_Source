import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SkyfallTestModule } from '../../../test.module';
import { CurrentQuarterDetailComponent } from 'app/entities/current-quarter/current-quarter-detail.component';
import { CurrentQuarter } from 'app/shared/model/current-quarter.model';

describe('Component Tests', () => {
  describe('CurrentQuarter Management Detail Component', () => {
    let comp: CurrentQuarterDetailComponent;
    let fixture: ComponentFixture<CurrentQuarterDetailComponent>;
    const route = ({ data: of({ currentQuarter: new CurrentQuarter(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SkyfallTestModule],
        declarations: [CurrentQuarterDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(CurrentQuarterDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CurrentQuarterDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.currentQuarter).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
