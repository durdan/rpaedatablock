import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ClientEmailList from './client-email-list';
import ClientEmailListDetail from './client-email-list-detail';
import ClientEmailListUpdate from './client-email-list-update';
import ClientEmailListDeleteDialog from './client-email-list-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ClientEmailListUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ClientEmailListUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ClientEmailListDetail} />
      <ErrorBoundaryRoute path={match.url} component={ClientEmailList} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={ClientEmailListDeleteDialog} />
  </>
);

export default Routes;
