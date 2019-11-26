import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'document-type',
        loadChildren: () => import('./document-type/document-type.module').then(m => m.SkyfallDocumentTypeModule)
      },
      {
        path: 'client',
        loadChildren: () => import('./client/client.module').then(m => m.SkyfallClientModule)
      },
      {
        path: 'course-state',
        loadChildren: () => import('./course-state/course-state.module').then(m => m.SkyfallCourseStateModule)
      },
      {
        path: 'working-day',
        loadChildren: () => import('./working-day/working-day.module').then(m => m.SkyfallWorkingDayModule)
      },
      {
        path: 'level-formation',
        loadChildren: () => import('./level-formation/level-formation.module').then(m => m.SkyfallLevelFormationModule)
      },
      {
        path: 'program',
        loadChildren: () => import('./program/program.module').then(m => m.SkyfallProgramModule)
      },
      {
        path: 'competition',
        loadChildren: () => import('./competition/competition.module').then(m => m.SkyfallCompetitionModule)
      },
      {
        path: 'availability-competition',
        loadChildren: () =>
          import('./availability-competition/availability-competition.module').then(m => m.SkyfallAvailabilityCompetitionModule)
      },
      {
        path: 'learning-result',
        loadChildren: () => import('./learning-result/learning-result.module').then(m => m.SkyfallLearningResultModule)
      },
      {
        path: 'result-seen',
        loadChildren: () => import('./result-seen/result-seen.module').then(m => m.SkyfallResultSeenModule)
      },
      {
        path: 'course',
        loadChildren: () => import('./course/course.module').then(m => m.SkyfallCourseModule)
      },
      {
        path: 'trimester',
        loadChildren: () => import('./trimester/trimester.module').then(m => m.SkyfallTrimesterModule)
      },
      {
        path: 'course-has-trimester',
        loadChildren: () => import('./course-has-trimester/course-has-trimester.module').then(m => m.SkyfallCourseHasTrimesterModule)
      },
      {
        path: 'planning',
        loadChildren: () => import('./planning/planning.module').then(m => m.SkyfallPlanningModule)
      },
      {
        path: 'trimester-planning',
        loadChildren: () => import('./trimester-planning/trimester-planning.module').then(m => m.SkyfallTrimesterPlanningModule)
      },
      {
        path: 'planning-activity',
        loadChildren: () => import('./planning-activity/planning-activity.module').then(m => m.SkyfallPlanningActivityModule)
      },
      {
        path: 'type-environment',
        loadChildren: () => import('./type-environment/type-environment.module').then(m => m.SkyfallTypeEnvironmentModule)
      },
      {
        path: 'sede',
        loadChildren: () => import('./sede/sede.module').then(m => m.SkyfallSedeModule)
      },
      {
        path: 'ambient',
        loadChildren: () => import('./ambient/ambient.module').then(m => m.SkyfallAmbientModule)
      },
      {
        path: 'limitation-environment',
        loadChildren: () => import('./limitation-environment/limitation-environment.module').then(m => m.SkyfallLimitationEnvironmentModule)
      },
      {
        path: 'year',
        loadChildren: () => import('./year/year.module').then(m => m.SkyfallYearModule)
      },
      {
        path: 'area',
        loadChildren: () => import('./area/area.module').then(m => m.SkyfallAreaModule)
      },
      {
        path: 'instructor',
        loadChildren: () => import('./instructor/instructor.module').then(m => m.SkyfallInstructorModule)
      },
      {
        path: 'linkage',
        loadChildren: () => import('./linkage/linkage.module').then(m => m.SkyfallLinkageModule)
      },
      {
        path: 'instructor-area',
        loadChildren: () => import('./instructor-area/instructor-area.module').then(m => m.SkyfallInstructorAreaModule)
      },
      {
        path: 'instructor-linking',
        loadChildren: () => import('./instructor-linking/instructor-linking.module').then(m => m.SkyfallInstructorLinkingModule)
      },
      {
        path: 'journey-instructor',
        loadChildren: () => import('./journey-instructor/journey-instructor.module').then(m => m.SkyfallJourneyInstructorModule)
      },
      {
        path: 'schedule-availability',
        loadChildren: () => import('./schedule-availability/schedule-availability.module').then(m => m.SkyfallScheduleAvailabilityModule)
      },
      {
        path: 'day',
        loadChildren: () => import('./day/day.module').then(m => m.SkyfallDayModule)
      },
      {
        path: 'time-study',
        loadChildren: () => import('./time-study/time-study.module').then(m => m.SkyfallTimeStudyModule)
      },
      {
        path: 'current-quarter',
        loadChildren: () => import('./current-quarter/current-quarter.module').then(m => m.SkyfallCurrentQuarterModule)
      },
      {
        path: 'schedule-version',
        loadChildren: () => import('./schedule-version/schedule-version.module').then(m => m.SkyfallScheduleVersionModule)
      },
      {
        path: 'modality',
        loadChildren: () => import('./modality/modality.module').then(m => m.SkyfallModalityModule)
      },
      {
        path: 'proyect',
        loadChildren: () => import('./proyect/proyect.module').then(m => m.SkyfallProyectModule)
      },
      {
        path: 'phase',
        loadChildren: () => import('./phase/phase.module').then(m => m.SkyfallPhaseModule)
      },
      {
        path: 'proyect-activity',
        loadChildren: () => import('./proyect-activity/proyect-activity.module').then(m => m.SkyfallProyectActivityModule)
      },
      {
        path: 'schedule',
        loadChildren: () => import('./schedule/schedule.module').then(m => m.SkyfallScheduleModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class SkyfallEntityModule {}
