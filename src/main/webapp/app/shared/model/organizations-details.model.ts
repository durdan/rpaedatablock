import { Moment } from 'moment';

export interface IOrganizationsDetails {
  id?: number;
  organizationName?: string;
  description?: string;
  organisationAddress?: string;
  organisationEmail?: string;
  isActive?: number;
  emailServerHost?: string;
  emailServerPort?: number;
  emailServerUserId?: string;
  emailServerPassword?: string;
  isEmailServerAccessAllowed?: number;
  createDate?: Moment;
  createdBy?: string;
  updateDate?: Moment;
  updatedBy?: string;
}

export const defaultValue: Readonly<IOrganizationsDetails> = {};
