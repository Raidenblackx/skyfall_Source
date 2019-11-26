import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SkyfallSharedModule } from 'app/shared/shared.module';
import { TrimesterPlanningComponent } from './trimester-planning.component';
import { TrimesterPlanningDetailComponent } from './trimester-planning-detail.component';
import { TrimesterPlanningUpdateComponent } from './trimester-planning-update.component';
import { TrimesterPlanningDeleteDialogComponent } from './trimester-planning-delete-dialog.component';
import { trimesterPlanningRoute } from './trimester-planning.route';

@NgModule({
  imports: [SkyfallSharedModule, RouterModule.forChild(trimesterPlanningRoute)],
  declarations: [
    TrimesterPlanningComponent,
    TrimesterPlanningDetailComponent,
    TrimesterPlanningUpdateComponent,
    TrimesterPlanningDeleteDialogComponent
  ],
  entryComponents: [TrimesterPlanningDeleteDialogComponent]
})
export class SkyfallTrimesterPlanningModule {}
