import { IPlanningActivity } from 'app/shared/model/planning-activity.model';
import { State } from 'app/shared/model/enumerations/state.model';

export interface IProyectActivity {
  id?: number;
  numberProyectActivity?: number;
  descriptionActivity?: string;
  stateProyectActivity?: State;
  planningActivities?: IPlanningActivity[];
  phaseNamePhase?: string;
  phaseId?: number;
}

export class ProyectActivity implements IProyectActivity {
  constructor(
    public id?: number,
    public numberProyectActivity?: number,
    public descriptionActivity?: string,
    public stateProyectActivity?: State,
    public planningActivities?: IPlanningActivity[],
    public phaseNamePhase?: string,
    public phaseId?: number
  ) {}
}
