import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SkyfallSharedModule } from 'app/shared/shared.module';
import { ProyectActivityComponent } from './proyect-activity.component';
import { ProyectActivityDetailComponent } from './proyect-activity-detail.component';
import { ProyectActivityUpdateComponent } from './proyect-activity-update.component';
import { ProyectActivityDeleteDialogComponent } from './proyect-activity-delete-dialog.component';
import { proyectActivityRoute } from './proyect-activity.route';

@NgModule({
  imports: [SkyfallSharedModule, RouterModule.forChild(proyectActivityRoute)],
  declarations: [
    ProyectActivityComponent,
    ProyectActivityDetailComponent,
    ProyectActivityUpdateComponent,
    ProyectActivityDeleteDialogComponent
  ],
  entryComponents: [ProyectActivityDeleteDialogComponent]
})
export class SkyfallProyectActivityModule {}
