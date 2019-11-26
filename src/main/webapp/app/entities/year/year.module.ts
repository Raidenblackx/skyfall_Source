import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SkyfallSharedModule } from 'app/shared/shared.module';
import { YearComponent } from './year.component';
import { YearDetailComponent } from './year-detail.component';
import { YearUpdateComponent } from './year-update.component';
import { YearDeleteDialogComponent } from './year-delete-dialog.component';
import { yearRoute } from './year.route';

@NgModule({
  imports: [SkyfallSharedModule, RouterModule.forChild(yearRoute)],
  declarations: [YearComponent, YearDetailComponent, YearUpdateComponent, YearDeleteDialogComponent],
  entryComponents: [YearDeleteDialogComponent]
})
export class SkyfallYearModule {}
