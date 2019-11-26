import { Moment } from 'moment';

export interface ISchedule {
  id?: number;
  startTime?: Moment;
  endTime?: Moment;
  courseHasTrimesterId?: number;
  ambientNumberRoom?: string;
  ambientId?: number;
  instructorId?: number;
  dayNameDay?: string;
  dayId?: number;
  scheduleVersionNumberVersion?: string;
  scheduleVersionId?: number;
  modalityNameModality?: string;
  modalityId?: number;
}

export class Schedule implements ISchedule {
  constructor(
    public id?: number,
    public startTime?: Moment,
    public endTime?: Moment,
    public courseHasTrimesterId?: number,
    public ambientNumberRoom?: string,
    public ambientId?: number,
    public instructorId?: number,
    public dayNameDay?: string,
    public dayId?: number,
    public scheduleVersionNumberVersion?: string,
    public scheduleVersionId?: number,
    public modalityNameModality?: string,
    public modalityId?: number
  ) {}
}
