import { ICourse } from 'app/shared/model/course.model';
import { ITrimester } from 'app/shared/model/trimester.model';
import { State } from 'app/shared/model/enumerations/state.model';

export interface IWorkingDay {
  id?: number;
  initialWorkingDay?: string;
  nameWorkingDay?: string;
  description?: string;
  imagenUrlContentType?: string;
  imagenUrl?: any;
  stateWorkingDay?: State;
  courses?: ICourse[];
  trimesters?: ITrimester[];
}

export class WorkingDay implements IWorkingDay {
  constructor(
    public id?: number,
    public initialWorkingDay?: string,
    public nameWorkingDay?: string,
    public description?: string,
    public imagenUrlContentType?: string,
    public imagenUrl?: any,
    public stateWorkingDay?: State,
    public courses?: ICourse[],
    public trimesters?: ITrimester[]
  ) {}
}
