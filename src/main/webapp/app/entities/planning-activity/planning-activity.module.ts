import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SkyfallSharedModule } from 'app/shared/shared.module';
import { PlanningActivityComponent } from './planning-activity.component';
import { PlanningActivityDetailComponent } from './planning-activity-detail.component';
import { PlanningActivityUpdateComponent } from './planning-activity-update.component';
import { PlanningActivityDeleteDialogComponent } from './planning-activity-delete-dialog.component';
import { planningActivityRoute } from './planning-activity.route';

@NgModule({
  imports: [SkyfallSharedModule, RouterModule.forChild(planningActivityRoute)],
  declarations: [
    PlanningActivityComponent,
    PlanningActivityDetailComponent,
    PlanningActivityUpdateComponent,
    PlanningActivityDeleteDialogComponent
  ],
  entryComponents: [PlanningActivityDeleteDialogComponent]
})
export class SkyfallPlanningActivityModule {}
