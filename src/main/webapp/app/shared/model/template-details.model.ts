import { Moment } from 'moment';

export interface ITemplateDetails {
  id?: number;
  templateName?: string;
  templateDescription?: string;
  templateType?: string;
  isActive?: number;
  createDate?: Moment;
  createdBy?: string;
  updateDate?: Moment;
  updatedBy?: string;
  clientId?: number;
}

export const defaultValue: Readonly<ITemplateDetails> = {};
