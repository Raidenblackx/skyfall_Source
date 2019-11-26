import { ITrimesterPlanning } from 'app/shared/model/trimester-planning.model';
import { ILimitationEnvironment } from 'app/shared/model/limitation-environment.model';
import { IResultSeen } from 'app/shared/model/result-seen.model';

export interface ILearningResult {
  id?: number;
  codeResult?: string;
  denomination?: string;
  trimesterPlannings?: ITrimesterPlanning[];
  limitationEnviroments?: ILimitationEnvironment[];
  resultSeens?: IResultSeen[];
  competitionCodeCompetition?: string;
  competitionId?: number;
}

export class LearningResult implements ILearningResult {
  constructor(
    public id?: number,
    public codeResult?: string,
    public denomination?: string,
    public trimesterPlannings?: ITrimesterPlanning[],
    public limitationEnviroments?: ILimitationEnvironment[],
    public resultSeens?: IResultSeen[],
    public competitionCodeCompetition?: string,
    public competitionId?: number
  ) {}
}
