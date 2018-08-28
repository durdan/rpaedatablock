import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import OrganizationsDetails from './organizations-details';
import OrganizationsDetailsDetail from './organizations-details-detail';
import OrganizationsDetailsUpdate from './organizations-details-update';
import OrganizationsDetailsDeleteDialog from './organizations-details-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={OrganizationsDetailsUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={OrganizationsDetailsUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={OrganizationsDetailsDetail} />
      <ErrorBoundaryRoute path={match.url} component={OrganizationsDetails} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={OrganizationsDetailsDeleteDialog} />
  </>
);

export default Routes;
