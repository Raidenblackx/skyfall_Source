import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SkyfallSharedModule } from 'app/shared/shared.module';
import { CourseHasTrimesterComponent } from './course-has-trimester.component';
import { CourseHasTrimesterDetailComponent } from './course-has-trimester-detail.component';
import { CourseHasTrimesterUpdateComponent } from './course-has-trimester-update.component';
import { CourseHasTrimesterDeleteDialogComponent } from './course-has-trimester-delete-dialog.component';
import { courseHasTrimesterRoute } from './course-has-trimester.route';

@NgModule({
  imports: [SkyfallSharedModule, RouterModule.forChild(courseHasTrimesterRoute)],
  declarations: [
    CourseHasTrimesterComponent,
    CourseHasTrimesterDetailComponent,
    CourseHasTrimesterUpdateComponent,
    CourseHasTrimesterDeleteDialogComponent
  ],
  entryComponents: [CourseHasTrimesterDeleteDialogComponent]
})
export class SkyfallCourseHasTrimesterModule {}
