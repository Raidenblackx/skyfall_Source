import { IAmbient } from 'app/shared/model/ambient.model';
import { State } from 'app/shared/model/enumerations/state.model';

export interface ITypeEnvironment {
  id?: number;
  type?: string;
  description?: string;
  stateTypeEnvironment?: State;
  ambients?: IAmbient[];
}

export class TypeEnvironment implements ITypeEnvironment {
  constructor(
    public id?: number,
    public type?: string,
    public description?: string,
    public stateTypeEnvironment?: State,
    public ambients?: IAmbient[]
  ) {}
}
