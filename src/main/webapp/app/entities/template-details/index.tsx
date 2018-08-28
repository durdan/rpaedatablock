import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import TemplateDetails from './template-details';
import TemplateDetailsDetail from './template-details-detail';
import TemplateDetailsUpdate from './template-details-update';
import TemplateDetailsDeleteDialog from './template-details-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={TemplateDetailsUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={TemplateDetailsUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={TemplateDetailsDetail} />
      <ErrorBoundaryRoute path={match.url} component={TemplateDetails} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={TemplateDetailsDeleteDialog} />
  </>
);

export default Routes;
