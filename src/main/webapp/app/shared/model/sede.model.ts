import { IAmbient } from 'app/shared/model/ambient.model';
import { State } from 'app/shared/model/enumerations/state.model';

export interface ISede {
  id?: number;
  nameSede?: string;
  description?: string;
  stateSede?: State;
  ambients?: IAmbient[];
}

export class Sede implements ISede {
  constructor(
    public id?: number,
    public nameSede?: string,
    public description?: string,
    public stateSede?: State,
    public ambients?: IAmbient[]
  ) {}
}
