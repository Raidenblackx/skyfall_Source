import { IInstructorLinking } from 'app/shared/model/instructor-linking.model';
import { ICurrentQuarter } from 'app/shared/model/current-quarter.model';
import { State } from 'app/shared/model/enumerations/state.model';

export interface IYear {
  id?: number;
  numberYear?: number;
  stateYear?: State;
  instructorLinkings?: IInstructorLinking[];
  currentQuarters?: ICurrentQuarter[];
}

export class Year implements IYear {
  constructor(
    public id?: number,
    public numberYear?: number,
    public stateYear?: State,
    public instructorLinkings?: IInstructorLinking[],
    public currentQuarters?: ICurrentQuarter[]
  ) {}
}
