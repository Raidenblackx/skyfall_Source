import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SkyfallSharedModule } from 'app/shared/shared.module';
import { ResultSeenComponent } from './result-seen.component';
import { ResultSeenDetailComponent } from './result-seen-detail.component';
import { ResultSeenUpdateComponent } from './result-seen-update.component';
import { ResultSeenDeleteDialogComponent } from './result-seen-delete-dialog.component';
import { resultSeenRoute } from './result-seen.route';

@NgModule({
  imports: [SkyfallSharedModule, RouterModule.forChild(resultSeenRoute)],
  declarations: [ResultSeenComponent, ResultSeenDetailComponent, ResultSeenUpdateComponent, ResultSeenDeleteDialogComponent],
  entryComponents: [ResultSeenDeleteDialogComponent]
})
export class SkyfallResultSeenModule {}
