import React from 'react';
import { Switch } from 'react-router-dom';

// tslint:disable-next-line:no-unused-variable
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import OrganizationsDetails from './organizations-details';
import OrgEmailConfig from './org-email-config';
import Client from './client';
import ClientEmailList from './client-email-list';
import TemplateDetails from './template-details';
import TemplateFields from './template-fields';
import EmailProcessing from './email-processing';
import EmailAttachment from './email-attachment';
import EmailProcessingError from './email-processing-error';
import UploadFiles from './upload-files';
import FileForOCRProcessing from './file-for-ocr-processing';
import Transaction from './transaction';
import OcrProcessingError from './ocr-processing-error';
import ClientDataOcr from './client-data-ocr';
/* jhipster-needle-add-route-import - JHipster will add routes here */

const Routes = ({ match }) => (
  <div>
    <Switch>
      {/* prettier-ignore */}
      <ErrorBoundaryRoute path={`${match.url}/organizations-details`} component={OrganizationsDetails} />
      <ErrorBoundaryRoute path={`${match.url}/org-email-config`} component={OrgEmailConfig} />
      <ErrorBoundaryRoute path={`${match.url}/client`} component={Client} />
      <ErrorBoundaryRoute path={`${match.url}/client-email-list`} component={ClientEmailList} />
      <ErrorBoundaryRoute path={`${match.url}/template-details`} component={TemplateDetails} />
      <ErrorBoundaryRoute path={`${match.url}/template-fields`} component={TemplateFields} />
      <ErrorBoundaryRoute path={`${match.url}/email-processing`} component={EmailProcessing} />
      <ErrorBoundaryRoute path={`${match.url}/email-attachment`} component={EmailAttachment} />
      <ErrorBoundaryRoute path={`${match.url}/email-processing-error`} component={EmailProcessingError} />
      <ErrorBoundaryRoute path={`${match.url}/upload-files`} component={UploadFiles} />
      <ErrorBoundaryRoute path={`${match.url}/file-for-ocr-processing`} component={FileForOCRProcessing} />
      <ErrorBoundaryRoute path={`${match.url}/transaction`} component={Transaction} />
      <ErrorBoundaryRoute path={`${match.url}/ocr-processing-error`} component={OcrProcessingError} />
      <ErrorBoundaryRoute path={`${match.url}/client-data-ocr`} component={ClientDataOcr} />
      {/* jhipster-needle-add-route-path - JHipster will routes here */}
    </Switch>
  </div>
);

export default Routes;
