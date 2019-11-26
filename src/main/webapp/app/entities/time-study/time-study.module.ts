import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SkyfallSharedModule } from 'app/shared/shared.module';
import { TimeStudyComponent } from './time-study.component';
import { TimeStudyDetailComponent } from './time-study-detail.component';
import { TimeStudyUpdateComponent } from './time-study-update.component';
import { TimeStudyDeleteDialogComponent } from './time-study-delete-dialog.component';
import { timeStudyRoute } from './time-study.route';

@NgModule({
  imports: [SkyfallSharedModule, RouterModule.forChild(timeStudyRoute)],
  declarations: [TimeStudyComponent, TimeStudyDetailComponent, TimeStudyUpdateComponent, TimeStudyDeleteDialogComponent],
  entryComponents: [TimeStudyDeleteDialogComponent]
})
export class SkyfallTimeStudyModule {}
