import { Moment } from 'moment';
import { IScheduleVersion } from 'app/shared/model/schedule-version.model';
import { State } from 'app/shared/model/enumerations/state.model';

export interface ICurrentQuarter {
  id?: number;
  scheduledQuarter?: number;
  startDate?: Moment;
  endDate?: Moment;
  stateCurrentQuarter?: State;
  scheduleVersions?: IScheduleVersion[];
  yearNumberYear?: string;
  yearId?: number;
}

export class CurrentQuarter implements ICurrentQuarter {
  constructor(
    public id?: number,
    public scheduledQuarter?: number,
    public startDate?: Moment,
    public endDate?: Moment,
    public stateCurrentQuarter?: State,
    public scheduleVersions?: IScheduleVersion[],
    public yearNumberYear?: string,
    public yearId?: number
  ) {}
}
