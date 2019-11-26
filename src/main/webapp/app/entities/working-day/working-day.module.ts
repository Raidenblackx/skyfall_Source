import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SkyfallSharedModule } from 'app/shared/shared.module';
import { WorkingDayComponent } from './working-day.component';
import { WorkingDayDetailComponent } from './working-day-detail.component';
import { WorkingDayUpdateComponent } from './working-day-update.component';
import { WorkingDayDeleteDialogComponent } from './working-day-delete-dialog.component';
import { workingDayRoute } from './working-day.route';

@NgModule({
  imports: [SkyfallSharedModule, RouterModule.forChild(workingDayRoute)],
  declarations: [WorkingDayComponent, WorkingDayDetailComponent, WorkingDayUpdateComponent, WorkingDayDeleteDialogComponent],
  entryComponents: [WorkingDayDeleteDialogComponent]
})
export class SkyfallWorkingDayModule {}
