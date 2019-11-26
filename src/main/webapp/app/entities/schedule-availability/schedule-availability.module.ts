import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SkyfallSharedModule } from 'app/shared/shared.module';
import { ScheduleAvailabilityComponent } from './schedule-availability.component';
import { ScheduleAvailabilityDetailComponent } from './schedule-availability-detail.component';
import { ScheduleAvailabilityUpdateComponent } from './schedule-availability-update.component';
import { ScheduleAvailabilityDeleteDialogComponent } from './schedule-availability-delete-dialog.component';
import { scheduleAvailabilityRoute } from './schedule-availability.route';

@NgModule({
  imports: [SkyfallSharedModule, RouterModule.forChild(scheduleAvailabilityRoute)],
  declarations: [
    ScheduleAvailabilityComponent,
    ScheduleAvailabilityDetailComponent,
    ScheduleAvailabilityUpdateComponent,
    ScheduleAvailabilityDeleteDialogComponent
  ],
  entryComponents: [ScheduleAvailabilityDeleteDialogComponent]
})
export class SkyfallScheduleAvailabilityModule {}
