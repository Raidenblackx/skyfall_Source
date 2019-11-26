export interface IAvailabilityCompetition {
  id?: number;
  competitionCodeCompetition?: string;
  competitionId?: number;
  instructorLinkingId?: number;
}

export class AvailabilityCompetition implements IAvailabilityCompetition {
  constructor(
    public id?: number,
    public competitionCodeCompetition?: string,
    public competitionId?: number,
    public instructorLinkingId?: number
  ) {}
}
