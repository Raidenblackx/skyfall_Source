import { ISchedule } from 'app/shared/model/schedule.model';
import { State } from 'app/shared/model/enumerations/state.model';

export interface IModality {
  id?: number;
  nameModality?: string;
  color?: string;
  stateModality?: State;
  schedules?: ISchedule[];
}

export class Modality implements IModality {
  constructor(
    public id?: number,
    public nameModality?: string,
    public color?: string,
    public stateModality?: State,
    public schedules?: ISchedule[]
  ) {}
}
