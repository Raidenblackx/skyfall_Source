export interface IScheduleAvailability {
  id?: number;
  instructorLinkingId?: number;
  journeyInstructorNameDay?: string;
  journeyInstructorId?: number;
}

export class ScheduleAvailability implements IScheduleAvailability {
  constructor(
    public id?: number,
    public instructorLinkingId?: number,
    public journeyInstructorNameDay?: string,
    public journeyInstructorId?: number
  ) {}
}
