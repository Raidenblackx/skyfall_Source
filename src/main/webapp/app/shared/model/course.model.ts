import { Moment } from 'moment';
import { ICourseHasTrimester } from 'app/shared/model/course-has-trimester.model';

export interface ICourse {
  id?: number;
  courseNumber?: string;
  startDate?: Moment;
  endDate?: Moment;
  route?: string;
  courseHasTrimesters?: ICourseHasTrimester[];
  courseStateNameCourseState?: string;
  courseStateId?: number;
  workingDayNameWorkingDay?: string;
  workingDayId?: number;
  programNameProgram?: string;
  programId?: number;
}

export class Course implements ICourse {
  constructor(
    public id?: number,
    public courseNumber?: string,
    public startDate?: Moment,
    public endDate?: Moment,
    public route?: string,
    public courseHasTrimesters?: ICourseHasTrimester[],
    public courseStateNameCourseState?: string,
    public courseStateId?: number,
    public workingDayNameWorkingDay?: string,
    public workingDayId?: number,
    public programNameProgram?: string,
    public programId?: number
  ) {}
}
