import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SkyfallSharedModule } from 'app/shared/shared.module';
import { JourneyInstructorComponent } from './journey-instructor.component';
import { JourneyInstructorDetailComponent } from './journey-instructor-detail.component';
import { JourneyInstructorUpdateComponent } from './journey-instructor-update.component';
import { JourneyInstructorDeleteDialogComponent } from './journey-instructor-delete-dialog.component';
import { journeyInstructorRoute } from './journey-instructor.route';

@NgModule({
  imports: [SkyfallSharedModule, RouterModule.forChild(journeyInstructorRoute)],
  declarations: [
    JourneyInstructorComponent,
    JourneyInstructorDetailComponent,
    JourneyInstructorUpdateComponent,
    JourneyInstructorDeleteDialogComponent
  ],
  entryComponents: [JourneyInstructorDeleteDialogComponent]
})
export class SkyfallJourneyInstructorModule {}
