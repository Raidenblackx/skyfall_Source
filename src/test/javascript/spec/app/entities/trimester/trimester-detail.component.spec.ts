import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SkyfallTestModule } from '../../../test.module';
import { TrimesterDetailComponent } from 'app/entities/trimester/trimester-detail.component';
import { Trimester } from 'app/shared/model/trimester.model';

describe('Component Tests', () => {
  describe('Trimester Management Detail Component', () => {
    let comp: TrimesterDetailComponent;
    let fixture: ComponentFixture<TrimesterDetailComponent>;
    const route = ({ data: of({ trimester: new Trimester(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SkyfallTestModule],
        declarations: [TrimesterDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(TrimesterDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TrimesterDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.trimester).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
