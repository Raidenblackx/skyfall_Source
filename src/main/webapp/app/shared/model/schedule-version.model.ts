import { ISchedule } from 'app/shared/model/schedule.model';
import { State } from 'app/shared/model/enumerations/state.model';

export interface IScheduleVersion {
  id?: number;
  numberVersion?: string;
  stateScheduleVersion?: State;
  schedules?: ISchedule[];
  currentQuarterScheduledQuarter?: string;
  currentQuarterId?: number;
}

export class ScheduleVersion implements IScheduleVersion {
  constructor(
    public id?: number,
    public numberVersion?: string,
    public stateScheduleVersion?: State,
    public schedules?: ISchedule[],
    public currentQuarterScheduledQuarter?: string,
    public currentQuarterId?: number
  ) {}
}
