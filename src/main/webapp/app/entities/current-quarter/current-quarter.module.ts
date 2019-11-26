import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SkyfallSharedModule } from 'app/shared/shared.module';
import { CurrentQuarterComponent } from './current-quarter.component';
import { CurrentQuarterDetailComponent } from './current-quarter-detail.component';
import { CurrentQuarterUpdateComponent } from './current-quarter-update.component';
import { CurrentQuarterDeleteDialogComponent } from './current-quarter-delete-dialog.component';
import { currentQuarterRoute } from './current-quarter.route';

@NgModule({
  imports: [SkyfallSharedModule, RouterModule.forChild(currentQuarterRoute)],
  declarations: [
    CurrentQuarterComponent,
    CurrentQuarterDetailComponent,
    CurrentQuarterUpdateComponent,
    CurrentQuarterDeleteDialogComponent
  ],
  entryComponents: [CurrentQuarterDeleteDialogComponent]
})
export class SkyfallCurrentQuarterModule {}
