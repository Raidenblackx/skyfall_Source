import { IClient } from 'app/shared/model/client.model';
import { State } from 'app/shared/model/enumerations/state.model';

export interface IDocumentType {
  id?: number;
  initial?: string;
  documentName?: string;
  stateDocumentType?: State;
  clients?: IClient[];
}

export class DocumentType implements IDocumentType {
  constructor(
    public id?: number,
    public initial?: string,
    public documentName?: string,
    public stateDocumentType?: State,
    public clients?: IClient[]
  ) {}
}
