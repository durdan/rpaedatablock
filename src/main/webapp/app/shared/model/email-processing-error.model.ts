import { Moment } from 'moment';

export interface IEmailProcessingError {
  id?: number;
  errorMessage?: string;
  messageID?: string;
  receiveFrom?: string;
  receivedTime?: Moment;
  emailProcessingId?: number;
}

export const defaultValue: Readonly<IEmailProcessingError> = {};
