import { IInstructor } from 'app/shared/model/instructor.model';

export interface IClient {
  id?: number;
  documentNumber?: number;
  firstName?: string;
  secondName?: string;
  firstLastName?: string;
  secondLastName?: string;
  instructors?: IInstructor[];
  documentTypeDocumentName?: string;
  documentTypeId?: number;
  userLogin?: string;
  userId?: number;
}

export class Client implements IClient {
  constructor(
    public id?: number,
    public documentNumber?: number,
    public firstName?: string,
    public secondName?: string,
    public firstLastName?: string,
    public secondLastName?: string,
    public instructors?: IInstructor[],
    public documentTypeDocumentName?: string,
    public documentTypeId?: number,
    public userLogin?: string,
    public userId?: number
  ) {}
}
