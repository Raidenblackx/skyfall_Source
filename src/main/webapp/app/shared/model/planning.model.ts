import { Moment } from 'moment';
import { ITrimesterPlanning } from 'app/shared/model/trimester-planning.model';
import { State } from 'app/shared/model/enumerations/state.model';

export interface IPlanning {
  id?: number;
  code?: string;
  date?: Moment;
  statePlannig?: State;
  trimesterPlannings?: ITrimesterPlanning[];
}

export class Planning implements IPlanning {
  constructor(
    public id?: number,
    public code?: string,
    public date?: Moment,
    public statePlannig?: State,
    public trimesterPlannings?: ITrimesterPlanning[]
  ) {}
}
