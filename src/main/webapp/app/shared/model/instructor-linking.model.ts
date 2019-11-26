import { Moment } from 'moment';
import { IScheduleAvailability } from 'app/shared/model/schedule-availability.model';
import { IAvailabilityCompetition } from 'app/shared/model/availability-competition.model';

export interface IInstructorLinking {
  id?: number;
  startDate?: Moment;
  endDate?: Moment;
  scheduleAvailabilities?: IScheduleAvailability[];
  availabilityCompetitions?: IAvailabilityCompetition[];
  yearNumberYear?: string;
  yearId?: number;
  instructorId?: number;
  linkageTypeLink?: string;
  linkageId?: number;
}

export class InstructorLinking implements IInstructorLinking {
  constructor(
    public id?: number,
    public startDate?: Moment,
    public endDate?: Moment,
    public scheduleAvailabilities?: IScheduleAvailability[],
    public availabilityCompetitions?: IAvailabilityCompetition[],
    public yearNumberYear?: string,
    public yearId?: number,
    public instructorId?: number,
    public linkageTypeLink?: string,
    public linkageId?: number
  ) {}
}
