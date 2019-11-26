import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SkyfallSharedModule } from 'app/shared/shared.module';
import { TypeEnvironmentComponent } from './type-environment.component';
import { TypeEnvironmentDetailComponent } from './type-environment-detail.component';
import { TypeEnvironmentUpdateComponent } from './type-environment-update.component';
import { TypeEnvironmentDeleteDialogComponent } from './type-environment-delete-dialog.component';
import { typeEnvironmentRoute } from './type-environment.route';

@NgModule({
  imports: [SkyfallSharedModule, RouterModule.forChild(typeEnvironmentRoute)],
  declarations: [
    TypeEnvironmentComponent,
    TypeEnvironmentDetailComponent,
    TypeEnvironmentUpdateComponent,
    TypeEnvironmentDeleteDialogComponent
  ],
  entryComponents: [TypeEnvironmentDeleteDialogComponent]
})
export class SkyfallTypeEnvironmentModule {}
