import { IPhase } from 'app/shared/model/phase.model';
import { State } from 'app/shared/model/enumerations/state.model';

export interface IProyect {
  id?: number;
  codeProyect?: string;
  nameProyect?: string;
  stateProyect?: State;
  phases?: IPhase[];
  programNameProgram?: string;
  programId?: number;
}

export class Proyect implements IProyect {
  constructor(
    public id?: number,
    public codeProyect?: string,
    public nameProyect?: string,
    public stateProyect?: State,
    public phases?: IPhase[],
    public programNameProgram?: string,
    public programId?: number
  ) {}
}
