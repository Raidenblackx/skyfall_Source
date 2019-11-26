import { ISchedule } from 'app/shared/model/schedule.model';
import { IResultSeen } from 'app/shared/model/result-seen.model';

export interface ICourseHasTrimester {
  id?: number;
  schedules?: ISchedule[];
  resultSeens?: IResultSeen[];
  courseCourseNumber?: string;
  courseId?: number;
  trimesterNameTrimester?: string;
  trimesterId?: number;
}

export class CourseHasTrimester implements ICourseHasTrimester {
  constructor(
    public id?: number,
    public schedules?: ISchedule[],
    public resultSeens?: IResultSeen[],
    public courseCourseNumber?: string,
    public courseId?: number,
    public trimesterNameTrimester?: string,
    public trimesterId?: number
  ) {}
}
