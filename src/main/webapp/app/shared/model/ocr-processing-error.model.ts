import { Moment } from 'moment';

export interface IOcrProcessingError {
  id?: number;
  errorMessage?: string;
  createdDateTime?: Moment;
  errorType?: string;
  transactionId?: number;
}

export const defaultValue: Readonly<IOcrProcessingError> = {};
