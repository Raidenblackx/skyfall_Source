import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SkyfallSharedModule } from 'app/shared/shared.module';
import { AvailabilityCompetitionComponent } from './availability-competition.component';
import { AvailabilityCompetitionDetailComponent } from './availability-competition-detail.component';
import { AvailabilityCompetitionUpdateComponent } from './availability-competition-update.component';
import { AvailabilityCompetitionDeleteDialogComponent } from './availability-competition-delete-dialog.component';
import { availabilityCompetitionRoute } from './availability-competition.route';

@NgModule({
  imports: [SkyfallSharedModule, RouterModule.forChild(availabilityCompetitionRoute)],
  declarations: [
    AvailabilityCompetitionComponent,
    AvailabilityCompetitionDetailComponent,
    AvailabilityCompetitionUpdateComponent,
    AvailabilityCompetitionDeleteDialogComponent
  ],
  entryComponents: [AvailabilityCompetitionDeleteDialogComponent]
})
export class SkyfallAvailabilityCompetitionModule {}
