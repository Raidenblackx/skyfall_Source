import { ICourseHasTrimester } from 'app/shared/model/course-has-trimester.model';
import { ITrimesterPlanning } from 'app/shared/model/trimester-planning.model';
import { State } from 'app/shared/model/enumerations/state.model';

export interface ITrimester {
  id?: number;
  nameTrimester?: number;
  stateTrimester?: State;
  courseHasTrimesters?: ICourseHasTrimester[];
  trimesterPlannings?: ITrimesterPlanning[];
  workingDayNameWorkingDay?: string;
  workingDayId?: number;
  levelFormationLevel?: string;
  levelFormationId?: number;
}

export class Trimester implements ITrimester {
  constructor(
    public id?: number,
    public nameTrimester?: number,
    public stateTrimester?: State,
    public courseHasTrimesters?: ICourseHasTrimester[],
    public trimesterPlannings?: ITrimesterPlanning[],
    public workingDayNameWorkingDay?: string,
    public workingDayId?: number,
    public levelFormationLevel?: string,
    public levelFormationId?: number
  ) {}
}
