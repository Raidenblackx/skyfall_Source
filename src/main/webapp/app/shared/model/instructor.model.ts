import { IInstructorArea } from 'app/shared/model/instructor-area.model';
import { IInstructorLinking } from 'app/shared/model/instructor-linking.model';
import { ISchedule } from 'app/shared/model/schedule.model';
import { State } from 'app/shared/model/enumerations/state.model';

export interface IInstructor {
  id?: number;
  stateInstructor?: State;
  instructorAreas?: IInstructorArea[];
  instructorLinkings?: IInstructorLinking[];
  schedules?: ISchedule[];
  clientDocumentNumber?: string;
  clientId?: number;
}

export class Instructor implements IInstructor {
  constructor(
    public id?: number,
    public stateInstructor?: State,
    public instructorAreas?: IInstructorArea[],
    public instructorLinkings?: IInstructorLinking[],
    public schedules?: ISchedule[],
    public clientDocumentNumber?: string,
    public clientId?: number
  ) {}
}
