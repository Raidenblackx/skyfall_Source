import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SkyfallSharedModule } from 'app/shared/shared.module';
import { ProyectComponent } from './proyect.component';
import { ProyectDetailComponent } from './proyect-detail.component';
import { ProyectUpdateComponent } from './proyect-update.component';
import { ProyectDeleteDialogComponent } from './proyect-delete-dialog.component';
import { proyectRoute } from './proyect.route';

@NgModule({
  imports: [SkyfallSharedModule, RouterModule.forChild(proyectRoute)],
  declarations: [ProyectComponent, ProyectDetailComponent, ProyectUpdateComponent, ProyectDeleteDialogComponent],
  entryComponents: [ProyectDeleteDialogComponent]
})
export class SkyfallProyectModule {}
