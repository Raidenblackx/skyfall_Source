import { IInstructorArea } from 'app/shared/model/instructor-area.model';
import { State } from 'app/shared/model/enumerations/state.model';

export interface IArea {
  id?: number;
  nameArea?: string;
  urlLogoContentType?: string;
  urlLogo?: any;
  stateArea?: State;
  instructorAreas?: IInstructorArea[];
}

export class Area implements IArea {
  constructor(
    public id?: number,
    public nameArea?: string,
    public urlLogoContentType?: string,
    public urlLogo?: any,
    public stateArea?: State,
    public instructorAreas?: IInstructorArea[]
  ) {}
}
