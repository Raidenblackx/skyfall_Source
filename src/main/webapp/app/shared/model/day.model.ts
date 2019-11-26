import { ISchedule } from 'app/shared/model/schedule.model';
import { ITimeStudy } from 'app/shared/model/time-study.model';
import { State } from 'app/shared/model/enumerations/state.model';

export interface IDay {
  id?: number;
  nameDay?: string;
  stateDay?: State;
  schedules?: ISchedule[];
  timeStudies?: ITimeStudy[];
}

export class Day implements IDay {
  constructor(
    public id?: number,
    public nameDay?: string,
    public stateDay?: State,
    public schedules?: ISchedule[],
    public timeStudies?: ITimeStudy[]
  ) {}
}
