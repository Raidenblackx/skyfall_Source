export interface IResultSeen {
  id?: number;
  learningResultCodeResult?: string;
  learningResultId?: number;
  courseHasTrimesterId?: number;
}

export class ResultSeen implements IResultSeen {
  constructor(
    public id?: number,
    public learningResultCodeResult?: string,
    public learningResultId?: number,
    public courseHasTrimesterId?: number
  ) {}
}
