import { ILearningResult } from 'app/shared/model/learning-result.model';
import { IAvailabilityCompetition } from 'app/shared/model/availability-competition.model';

export interface ICompetition {
  id?: number;
  codeCompetition?: string;
  denomination?: string;
  learningResults?: ILearningResult[];
  availabilityCompetitions?: IAvailabilityCompetition[];
  programNameProgram?: string;
  programId?: number;
}

export class Competition implements ICompetition {
  constructor(
    public id?: number,
    public codeCompetition?: string,
    public denomination?: string,
    public learningResults?: ILearningResult[],
    public availabilityCompetitions?: IAvailabilityCompetition[],
    public programNameProgram?: string,
    public programId?: number
  ) {}
}
