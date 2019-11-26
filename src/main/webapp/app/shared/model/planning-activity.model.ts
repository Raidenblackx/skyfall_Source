export interface IPlanningActivity {
  id?: number;
  trimesterPlanningId?: number;
  proyectActivityNumberProyectActivity?: string;
  proyectActivityId?: number;
}

export class PlanningActivity implements IPlanningActivity {
  constructor(
    public id?: number,
    public trimesterPlanningId?: number,
    public proyectActivityNumberProyectActivity?: string,
    public proyectActivityId?: number
  ) {}
}
