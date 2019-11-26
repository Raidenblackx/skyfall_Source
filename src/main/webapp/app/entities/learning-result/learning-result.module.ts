import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SkyfallSharedModule } from 'app/shared/shared.module';
import { LearningResultComponent } from './learning-result.component';
import { LearningResultDetailComponent } from './learning-result-detail.component';
import { LearningResultUpdateComponent } from './learning-result-update.component';
import { LearningResultDeleteDialogComponent } from './learning-result-delete-dialog.component';
import { learningResultRoute } from './learning-result.route';

@NgModule({
  imports: [SkyfallSharedModule, RouterModule.forChild(learningResultRoute)],
  declarations: [
    LearningResultComponent,
    LearningResultDetailComponent,
    LearningResultUpdateComponent,
    LearningResultDeleteDialogComponent
  ],
  entryComponents: [LearningResultDeleteDialogComponent]
})
export class SkyfallLearningResultModule {}
