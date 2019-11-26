import { IPlanningActivity } from 'app/shared/model/planning-activity.model';

export interface ITrimesterPlanning {
  id?: number;
  planningActivities?: IPlanningActivity[];
  learningResultCodeResult?: string;
  learningResultId?: number;
  trimesterNameTrimester?: string;
  trimesterId?: number;
  planningCode?: string;
  planningId?: number;
}

export class TrimesterPlanning implements ITrimesterPlanning {
  constructor(
    public id?: number,
    public planningActivities?: IPlanningActivity[],
    public learningResultCodeResult?: string,
    public learningResultId?: number,
    public trimesterNameTrimester?: string,
    public trimesterId?: number,
    public planningCode?: string,
    public planningId?: number
  ) {}
}
