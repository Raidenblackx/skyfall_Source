import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SkyfallTestModule } from '../../../test.module';
import { LinkageDetailComponent } from 'app/entities/linkage/linkage-detail.component';
import { Linkage } from 'app/shared/model/linkage.model';

describe('Component Tests', () => {
  describe('Linkage Management Detail Component', () => {
    let comp: LinkageDetailComponent;
    let fixture: ComponentFixture<LinkageDetailComponent>;
    const route = ({ data: of({ linkage: new Linkage(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SkyfallTestModule],
        declarations: [LinkageDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(LinkageDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(LinkageDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.linkage).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
