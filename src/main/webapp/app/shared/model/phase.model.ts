import { IProyectActivity } from 'app/shared/model/proyect-activity.model';
import { State } from 'app/shared/model/enumerations/state.model';

export interface IPhase {
  id?: number;
  namePhase?: string;
  statePhase?: State;
  proyectActivities?: IProyectActivity[];
  proyectCodeProyect?: string;
  proyectId?: number;
}

export class Phase implements IPhase {
  constructor(
    public id?: number,
    public namePhase?: string,
    public statePhase?: State,
    public proyectActivities?: IProyectActivity[],
    public proyectCodeProyect?: string,
    public proyectId?: number
  ) {}
}
