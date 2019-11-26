import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SkyfallSharedModule } from 'app/shared/shared.module';
import { AmbientComponent } from './ambient.component';
import { AmbientDetailComponent } from './ambient-detail.component';
import { AmbientUpdateComponent } from './ambient-update.component';
import { AmbientDeleteDialogComponent } from './ambient-delete-dialog.component';
import { ambientRoute } from './ambient.route';

@NgModule({
  imports: [SkyfallSharedModule, RouterModule.forChild(ambientRoute)],
  declarations: [AmbientComponent, AmbientDetailComponent, AmbientUpdateComponent, AmbientDeleteDialogComponent],
  entryComponents: [AmbientDeleteDialogComponent]
})
export class SkyfallAmbientModule {}
