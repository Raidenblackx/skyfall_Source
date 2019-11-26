import { State } from 'app/shared/model/enumerations/state.model';

export interface IInstructorArea {
  id?: number;
  stateInstructorArea?: State;
  areaNameArea?: string;
  areaId?: number;
  instructorId?: number;
}

export class InstructorArea implements IInstructorArea {
  constructor(
    public id?: number,
    public stateInstructorArea?: State,
    public areaNameArea?: string,
    public areaId?: number,
    public instructorId?: number
  ) {}
}
