import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SkyfallSharedModule } from 'app/shared/shared.module';
import { ScheduleVersionComponent } from './schedule-version.component';
import { ScheduleVersionDetailComponent } from './schedule-version-detail.component';
import { ScheduleVersionUpdateComponent } from './schedule-version-update.component';
import { ScheduleVersionDeleteDialogComponent } from './schedule-version-delete-dialog.component';
import { scheduleVersionRoute } from './schedule-version.route';

@NgModule({
  imports: [SkyfallSharedModule, RouterModule.forChild(scheduleVersionRoute)],
  declarations: [
    ScheduleVersionComponent,
    ScheduleVersionDetailComponent,
    ScheduleVersionUpdateComponent,
    ScheduleVersionDeleteDialogComponent
  ],
  entryComponents: [ScheduleVersionDeleteDialogComponent]
})
export class SkyfallScheduleVersionModule {}
