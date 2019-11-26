import { ITimeStudy } from 'app/shared/model/time-study.model';
import { IScheduleAvailability } from 'app/shared/model/schedule-availability.model';
import { State } from 'app/shared/model/enumerations/state.model';

export interface IJourneyInstructor {
  id?: number;
  nameDay?: string;
  description?: string;
  stateJourneyInstructor?: State;
  timeStudies?: ITimeStudy[];
  scheduleAvailabilities?: IScheduleAvailability[];
}

export class JourneyInstructor implements IJourneyInstructor {
  constructor(
    public id?: number,
    public nameDay?: string,
    public description?: string,
    public stateJourneyInstructor?: State,
    public timeStudies?: ITimeStudy[],
    public scheduleAvailabilities?: IScheduleAvailability[]
  ) {}
}
