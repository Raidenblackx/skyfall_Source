import { ICourse } from 'app/shared/model/course.model';
import { State } from 'app/shared/model/enumerations/state.model';

export interface ICourseState {
  id?: number;
  nameCourseState?: string;
  stateCourseState?: State;
  courses?: ICourse[];
}

export class CourseState implements ICourseState {
  constructor(public id?: number, public nameCourseState?: string, public stateCourseState?: State, public courses?: ICourse[]) {}
}
