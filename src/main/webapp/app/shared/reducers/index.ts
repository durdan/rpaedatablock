import { combineReducers } from 'redux';
import { loadingBarReducer as loadingBar } from 'react-redux-loading-bar';

import authentication, { AuthenticationState } from './authentication';
import applicationProfile, { ApplicationProfileState } from './application-profile';

import administration, { AdministrationState } from 'app/modules/administration/administration.reducer';
import userManagement, { UserManagementState } from 'app/modules/administration/user-management/user-management.reducer';
import register, { RegisterState } from 'app/modules/account/register/register.reducer';
import activate, { ActivateState } from 'app/modules/account/activate/activate.reducer';
import password, { PasswordState } from 'app/modules/account/password/password.reducer';
import settings, { SettingsState } from 'app/modules/account/settings/settings.reducer';
import passwordReset, { PasswordResetState } from 'app/modules/account/password-reset/password-reset.reducer';
// prettier-ignore
import organizationsDetails, {
  OrganizationsDetailsState
} from 'app/entities/organizations-details/organizations-details.reducer';
// prettier-ignore
import orgEmailConfig, {
  OrgEmailConfigState
} from 'app/entities/org-email-config/org-email-config.reducer';
// prettier-ignore
import client, {
  ClientState
} from 'app/entities/client/client.reducer';
// prettier-ignore
import clientEmailList, {
  ClientEmailListState
} from 'app/entities/client-email-list/client-email-list.reducer';
// prettier-ignore
import templateDetails, {
  TemplateDetailsState
} from 'app/entities/template-details/template-details.reducer';
// prettier-ignore
import templateFields, {
  TemplateFieldsState
} from 'app/entities/template-fields/template-fields.reducer';
// prettier-ignore
import emailProcessing, {
  EmailProcessingState
} from 'app/entities/email-processing/email-processing.reducer';
// prettier-ignore
import emailAttachment, {
  EmailAttachmentState
} from 'app/entities/email-attachment/email-attachment.reducer';
// prettier-ignore
import emailProcessingError, {
  EmailProcessingErrorState
} from 'app/entities/email-processing-error/email-processing-error.reducer';
// prettier-ignore
import uploadFiles, {
  UploadFilesState
} from 'app/entities/upload-files/upload-files.reducer';
// prettier-ignore
import fileForOCRProcessing, {
  FileForOCRProcessingState
} from 'app/entities/file-for-ocr-processing/file-for-ocr-processing.reducer';
// prettier-ignore
import transaction, {
  TransactionState
} from 'app/entities/transaction/transaction.reducer';
// prettier-ignore
import ocrProcessingError, {
  OcrProcessingErrorState
} from 'app/entities/ocr-processing-error/ocr-processing-error.reducer';
// prettier-ignore
import clientDataOcr, {
  ClientDataOcrState
} from 'app/entities/client-data-ocr/client-data-ocr.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

export interface IRootState {
  readonly authentication: AuthenticationState;
  readonly applicationProfile: ApplicationProfileState;
  readonly administration: AdministrationState;
  readonly userManagement: UserManagementState;
  readonly register: RegisterState;
  readonly activate: ActivateState;
  readonly passwordReset: PasswordResetState;
  readonly password: PasswordState;
  readonly settings: SettingsState;
  readonly organizationsDetails: OrganizationsDetailsState;
  readonly orgEmailConfig: OrgEmailConfigState;
  readonly client: ClientState;
  readonly clientEmailList: ClientEmailListState;
  readonly templateDetails: TemplateDetailsState;
  readonly templateFields: TemplateFieldsState;
  readonly emailProcessing: EmailProcessingState;
  readonly emailAttachment: EmailAttachmentState;
  readonly emailProcessingError: EmailProcessingErrorState;
  readonly uploadFiles: UploadFilesState;
  readonly fileForOCRProcessing: FileForOCRProcessingState;
  readonly transaction: TransactionState;
  readonly ocrProcessingError: OcrProcessingErrorState;
  readonly clientDataOcr: ClientDataOcrState;
  /* jhipster-needle-add-reducer-type - JHipster will add reducer type here */
  readonly loadingBar: any;
}

const rootReducer = combineReducers<IRootState>({
  authentication,
  applicationProfile,
  administration,
  userManagement,
  register,
  activate,
  passwordReset,
  password,
  settings,
  organizationsDetails,
  orgEmailConfig,
  client,
  clientEmailList,
  templateDetails,
  templateFields,
  emailProcessing,
  emailAttachment,
  emailProcessingError,
  uploadFiles,
  fileForOCRProcessing,
  transaction,
  ocrProcessingError,
  clientDataOcr,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
  loadingBar
});

export default rootReducer;
