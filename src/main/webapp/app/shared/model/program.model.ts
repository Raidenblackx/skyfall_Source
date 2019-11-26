import { ICompetition } from 'app/shared/model/competition.model';
import { ICourse } from 'app/shared/model/course.model';
import { IProyect } from 'app/shared/model/proyect.model';
import { StateProgram } from 'app/shared/model/enumerations/state-program.model';

export interface IProgram {
  id?: number;
  codeProgram?: string;
  version?: string;
  nameProgram?: string;
  initial?: string;
  stateProgram?: StateProgram;
  competitions?: ICompetition[];
  courses?: ICourse[];
  proyects?: IProyect[];
  levelFormationLevel?: string;
  levelFormationId?: number;
}

export class Program implements IProgram {
  constructor(
    public id?: number,
    public codeProgram?: string,
    public version?: string,
    public nameProgram?: string,
    public initial?: string,
    public stateProgram?: StateProgram,
    public competitions?: ICompetition[],
    public courses?: ICourse[],
    public proyects?: IProyect[],
    public levelFormationLevel?: string,
    public levelFormationId?: number
  ) {}
}
