import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SkyfallSharedModule } from 'app/shared/shared.module';
import { PhaseComponent } from './phase.component';
import { PhaseDetailComponent } from './phase-detail.component';
import { PhaseUpdateComponent } from './phase-update.component';
import { PhaseDeleteDialogComponent } from './phase-delete-dialog.component';
import { phaseRoute } from './phase.route';

@NgModule({
  imports: [SkyfallSharedModule, RouterModule.forChild(phaseRoute)],
  declarations: [PhaseComponent, PhaseDetailComponent, PhaseUpdateComponent, PhaseDeleteDialogComponent],
  entryComponents: [PhaseDeleteDialogComponent]
})
export class SkyfallPhaseModule {}
