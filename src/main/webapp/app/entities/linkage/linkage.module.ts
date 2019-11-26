import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SkyfallSharedModule } from 'app/shared/shared.module';
import { LinkageComponent } from './linkage.component';
import { LinkageDetailComponent } from './linkage-detail.component';
import { LinkageUpdateComponent } from './linkage-update.component';
import { LinkageDeleteDialogComponent } from './linkage-delete-dialog.component';
import { linkageRoute } from './linkage.route';

@NgModule({
  imports: [SkyfallSharedModule, RouterModule.forChild(linkageRoute)],
  declarations: [LinkageComponent, LinkageDetailComponent, LinkageUpdateComponent, LinkageDeleteDialogComponent],
  entryComponents: [LinkageDeleteDialogComponent]
})
export class SkyfallLinkageModule {}
