import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SkyfallSharedModule } from 'app/shared/shared.module';
import { LevelFormationComponent } from './level-formation.component';
import { LevelFormationDetailComponent } from './level-formation-detail.component';
import { LevelFormationUpdateComponent } from './level-formation-update.component';
import { LevelFormationDeleteDialogComponent } from './level-formation-delete-dialog.component';
import { levelFormationRoute } from './level-formation.route';

@NgModule({
  imports: [SkyfallSharedModule, RouterModule.forChild(levelFormationRoute)],
  declarations: [
    LevelFormationComponent,
    LevelFormationDetailComponent,
    LevelFormationUpdateComponent,
    LevelFormationDeleteDialogComponent
  ],
  entryComponents: [LevelFormationDeleteDialogComponent]
})
export class SkyfallLevelFormationModule {}
