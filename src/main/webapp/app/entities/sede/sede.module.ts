import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SkyfallSharedModule } from 'app/shared/shared.module';
import { SedeComponent } from './sede.component';
import { SedeDetailComponent } from './sede-detail.component';
import { SedeUpdateComponent } from './sede-update.component';
import { SedeDeleteDialogComponent } from './sede-delete-dialog.component';
import { sedeRoute } from './sede.route';

@NgModule({
  imports: [SkyfallSharedModule, RouterModule.forChild(sedeRoute)],
  declarations: [SedeComponent, SedeDetailComponent, SedeUpdateComponent, SedeDeleteDialogComponent],
  entryComponents: [SedeDeleteDialogComponent]
})
export class SkyfallSedeModule {}
