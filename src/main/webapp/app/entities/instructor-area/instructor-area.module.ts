import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SkyfallSharedModule } from 'app/shared/shared.module';
import { InstructorAreaComponent } from './instructor-area.component';
import { InstructorAreaDetailComponent } from './instructor-area-detail.component';
import { InstructorAreaUpdateComponent } from './instructor-area-update.component';
import { InstructorAreaDeleteDialogComponent } from './instructor-area-delete-dialog.component';
import { instructorAreaRoute } from './instructor-area.route';

@NgModule({
  imports: [SkyfallSharedModule, RouterModule.forChild(instructorAreaRoute)],
  declarations: [
    InstructorAreaComponent,
    InstructorAreaDetailComponent,
    InstructorAreaUpdateComponent,
    InstructorAreaDeleteDialogComponent
  ],
  entryComponents: [InstructorAreaDeleteDialogComponent]
})
export class SkyfallInstructorAreaModule {}
