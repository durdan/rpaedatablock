import { Moment } from 'moment';

export interface IUploadFiles {
  id?: number;
  fileName?: string;
  fileExtension?: string;
  uploadBy?: string;
  uploadDateTime?: Moment;
  uploadLocation?: string;
  clientId?: number;
  fileForOCRProcessingId?: number;
}

export const defaultValue: Readonly<IUploadFiles> = {};
