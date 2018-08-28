export interface ITemplateFields {
  id?: number;
  fieldName?: string;
  fieldZoneMinX?: number;
  fieldZoneMinY?: number;
  fieldZoneMaxX?: number;
  fieldZoneMaxY?: number;
  fieldValidationRequire?: number;
  fieldValidationRule?: string;
  fieldLocation?: number;
  templateDetailsId?: number;
}

export const defaultValue: Readonly<ITemplateFields> = {};
