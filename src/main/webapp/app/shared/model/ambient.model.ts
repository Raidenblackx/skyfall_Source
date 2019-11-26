import { ILimitationEnvironment } from 'app/shared/model/limitation-environment.model';
import { ISchedule } from 'app/shared/model/schedule.model';
import { Limitation } from 'app/shared/model/enumerations/limitation.model';
import { State } from 'app/shared/model/enumerations/state.model';

export interface IAmbient {
  id?: number;
  numberRoom?: string;
  description?: string;
  limitation?: Limitation;
  stateAmbient?: State;
  limitationEnvironments?: ILimitationEnvironment[];
  schedules?: ISchedule[];
  typeEnvironmentType?: string;
  typeEnvironmentId?: number;
  sedeNameSede?: string;
  sedeId?: number;
}

export class Ambient implements IAmbient {
  constructor(
    public id?: number,
    public numberRoom?: string,
    public description?: string,
    public limitation?: Limitation,
    public stateAmbient?: State,
    public limitationEnvironments?: ILimitationEnvironment[],
    public schedules?: ISchedule[],
    public typeEnvironmentType?: string,
    public typeEnvironmentId?: number,
    public sedeNameSede?: string,
    public sedeId?: number
  ) {}
}
