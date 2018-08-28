import { Moment } from 'moment';

export interface IClient {
  id?: number;
  clientName?: string;
  description?: string;
  clientAddress?: string;
  clientContactEmailAddress?: string;
  isActive?: number;
  createDate?: Moment;
  createdBy?: string;
  updateDate?: Moment;
  updatedBy?: string;
  organizationsdetailsId?: number;
}

export const defaultValue: Readonly<IClient> = {};
