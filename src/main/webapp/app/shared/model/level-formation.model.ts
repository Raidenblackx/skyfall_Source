import { IProgram } from 'app/shared/model/program.model';
import { ITrimester } from 'app/shared/model/trimester.model';
import { State } from 'app/shared/model/enumerations/state.model';

export interface ILevelFormation {
  id?: number;
  level?: string;
  stateLevelFormation?: State;
  programs?: IProgram[];
  trimesters?: ITrimester[];
}

export class LevelFormation implements ILevelFormation {
  constructor(
    public id?: number,
    public level?: string,
    public stateLevelFormation?: State,
    public programs?: IProgram[],
    public trimesters?: ITrimester[]
  ) {}
}
