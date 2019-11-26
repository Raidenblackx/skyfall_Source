export interface ITimeStudy {
  id?: number;
  startTime?: number;
  endTime?: number;
  journeyInstructorNameDay?: string;
  journeyInstructorId?: number;
  dayNameDay?: string;
  dayId?: number;
}

export class TimeStudy implements ITimeStudy {
  constructor(
    public id?: number,
    public startTime?: number,
    public endTime?: number,
    public journeyInstructorNameDay?: string,
    public journeyInstructorId?: number,
    public dayNameDay?: string,
    public dayId?: number
  ) {}
}
