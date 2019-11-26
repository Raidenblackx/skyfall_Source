import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SkyfallTestModule } from '../../../test.module';
import { AvailabilityCompetitionDetailComponent } from 'app/entities/availability-competition/availability-competition-detail.component';
import { AvailabilityCompetition } from 'app/shared/model/availability-competition.model';

describe('Component Tests', () => {
  describe('AvailabilityCompetition Management Detail Component', () => {
    let comp: AvailabilityCompetitionDetailComponent;
    let fixture: ComponentFixture<AvailabilityCompetitionDetailComponent>;
    const route = ({ data: of({ availabilityCompetition: new AvailabilityCompetition(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SkyfallTestModule],
        declarations: [AvailabilityCompetitionDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(AvailabilityCompetitionDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(AvailabilityCompetitionDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.availabilityCompetition).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
