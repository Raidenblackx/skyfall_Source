export interface ILimitationEnvironment {
  id?: number;
  learningResultCodeResult?: string;
  learningResultId?: number;
  ambientNumberRoom?: string;
  ambientId?: number;
}

export class LimitationEnvironment implements ILimitationEnvironment {
  constructor(
    public id?: number,
    public learningResultCodeResult?: string,
    public learningResultId?: number,
    public ambientNumberRoom?: string,
    public ambientId?: number
  ) {}
}
