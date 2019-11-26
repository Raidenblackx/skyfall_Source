import { IInstructorLinking } from 'app/shared/model/instructor-linking.model';
import { State } from 'app/shared/model/enumerations/state.model';

export interface ILinkage {
  id?: number;
  typeLink?: string;
  hours?: number;
  stateLink?: State;
  instructorLinkings?: IInstructorLinking[];
}

export class Linkage implements ILinkage {
  constructor(
    public id?: number,
    public typeLink?: string,
    public hours?: number,
    public stateLink?: State,
    public instructorLinkings?: IInstructorLinking[]
  ) {}
}
