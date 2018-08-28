import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import EmailProcessing from './email-processing';
import EmailProcessingDetail from './email-processing-detail';
import EmailProcessingUpdate from './email-processing-update';
import EmailProcessingDeleteDialog from './email-processing-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={EmailProcessingUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={EmailProcessingUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={EmailProcessingDetail} />
      <ErrorBoundaryRoute path={match.url} component={EmailProcessing} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={EmailProcessingDeleteDialog} />
  </>
);

export default Routes;
