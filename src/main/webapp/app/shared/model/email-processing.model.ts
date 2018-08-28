import { Moment } from 'moment';

export interface IEmailProcessing {
  id?: number;
  messageId?: string;
  receiveFrom?: string;
  receivedTime?: Moment;
  numberOfAttachments?: string;
  clientEmailListId?: number;
  emailProcessingErrorId?: number;
}

export const defaultValue: Readonly<IEmailProcessing> = {};
