import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SkyfallSharedModule } from 'app/shared/shared.module';
import { CourseStateComponent } from './course-state.component';
import { CourseStateDetailComponent } from './course-state-detail.component';
import { CourseStateUpdateComponent } from './course-state-update.component';
import { CourseStateDeleteDialogComponent } from './course-state-delete-dialog.component';
import { courseStateRoute } from './course-state.route';

@NgModule({
  imports: [SkyfallSharedModule, RouterModule.forChild(courseStateRoute)],
  declarations: [CourseStateComponent, CourseStateDetailComponent, CourseStateUpdateComponent, CourseStateDeleteDialogComponent],
  entryComponents: [CourseStateDeleteDialogComponent]
})
export class SkyfallCourseStateModule {}
