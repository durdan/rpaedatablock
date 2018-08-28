export interface IEmailAttachment {
  id?: number;
  fileName?: string;
  fileExtension?: string;
  fileLocation?: string;
  emailProcessingId?: number;
  fileForOCRProcessingId?: number;
}

export const defaultValue: Readonly<IEmailAttachment> = {};
