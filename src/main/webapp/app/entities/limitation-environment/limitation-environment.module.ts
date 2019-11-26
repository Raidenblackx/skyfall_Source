import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SkyfallSharedModule } from 'app/shared/shared.module';
import { LimitationEnvironmentComponent } from './limitation-environment.component';
import { LimitationEnvironmentDetailComponent } from './limitation-environment-detail.component';
import { LimitationEnvironmentUpdateComponent } from './limitation-environment-update.component';
import { LimitationEnvironmentDeleteDialogComponent } from './limitation-environment-delete-dialog.component';
import { limitationEnvironmentRoute } from './limitation-environment.route';

@NgModule({
  imports: [SkyfallSharedModule, RouterModule.forChild(limitationEnvironmentRoute)],
  declarations: [
    LimitationEnvironmentComponent,
    LimitationEnvironmentDetailComponent,
    LimitationEnvironmentUpdateComponent,
    LimitationEnvironmentDeleteDialogComponent
  ],
  entryComponents: [LimitationEnvironmentDeleteDialogComponent]
})
export class SkyfallLimitationEnvironmentModule {}
