import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SkyfallSharedModule } from 'app/shared/shared.module';
import { TrimesterComponent } from './trimester.component';
import { TrimesterDetailComponent } from './trimester-detail.component';
import { TrimesterUpdateComponent } from './trimester-update.component';
import { TrimesterDeleteDialogComponent } from './trimester-delete-dialog.component';
import { trimesterRoute } from './trimester.route';

@NgModule({
  imports: [SkyfallSharedModule, RouterModule.forChild(trimesterRoute)],
  declarations: [TrimesterComponent, TrimesterDetailComponent, TrimesterUpdateComponent, TrimesterDeleteDialogComponent],
  entryComponents: [TrimesterDeleteDialogComponent]
})
export class SkyfallTrimesterModule {}
