export interface IClientDataOcr {
  id?: number;
  keyName?: string;
  value?: string;
  transactionId?: number;
}

export const defaultValue: Readonly<IClientDataOcr> = {};
