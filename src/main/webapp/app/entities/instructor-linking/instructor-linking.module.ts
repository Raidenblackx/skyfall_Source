import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SkyfallSharedModule } from 'app/shared/shared.module';
import { InstructorLinkingComponent } from './instructor-linking.component';
import { InstructorLinkingDetailComponent } from './instructor-linking-detail.component';
import { InstructorLinkingUpdateComponent } from './instructor-linking-update.component';
import { InstructorLinkingDeleteDialogComponent } from './instructor-linking-delete-dialog.component';
import { instructorLinkingRoute } from './instructor-linking.route';

@NgModule({
  imports: [SkyfallSharedModule, RouterModule.forChild(instructorLinkingRoute)],
  declarations: [
    InstructorLinkingComponent,
    InstructorLinkingDetailComponent,
    InstructorLinkingUpdateComponent,
    InstructorLinkingDeleteDialogComponent
  ],
  entryComponents: [InstructorLinkingDeleteDialogComponent]
})
export class SkyfallInstructorLinkingModule {}
