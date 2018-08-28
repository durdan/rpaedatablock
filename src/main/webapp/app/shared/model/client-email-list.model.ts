export interface IClientEmailList {
  id?: number;
  emailAddress?: string;
  description?: string;
  isActive?: number;
  clientId?: number;
}

export const defaultValue: Readonly<IClientEmailList> = {};
