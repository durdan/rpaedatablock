import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAllAction, TextFormat, getSortState, IPaginationBaseState, getPaginationItemsNumber, JhiPagination } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './organizations-details.reducer';
import { IOrganizationsDetails } from 'app/shared/model/organizations-details.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface IOrganizationsDetailsProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export type IOrganizationsDetailsState = IPaginationBaseState;

export class OrganizationsDetails extends React.Component<IOrganizationsDetailsProps, IOrganizationsDetailsState> {
  state: IOrganizationsDetailsState = {
    ...getSortState(this.props.location, ITEMS_PER_PAGE)
  };

  componentDidMount() {
    this.getEntities();
  }

  sort = prop => () => {
    this.setState(
      {
        order: this.state.order === 'asc' ? 'desc' : 'asc',
        sort: prop
      },
      () => this.sortEntities()
    );
  };

  sortEntities() {
    this.getEntities();
    this.props.history.push(`${this.props.location.pathname}?page=${this.state.activePage}&sort=${this.state.sort},${this.state.order}`);
  }

  handlePagination = activePage => this.setState({ activePage }, () => this.sortEntities());

  getEntities = () => {
    const { activePage, itemsPerPage, sort, order } = this.state;
    this.props.getEntities(activePage - 1, itemsPerPage, `${sort},${order}`);
  };

  render() {
    const { organizationsDetailsList, match, totalItems } = this.props;
    return (
      <div>
        <h2 id="organizations-details-heading">
          Organizations Details
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp; Create new Organizations Details
          </Link>
        </h2>
        <div className="table-responsive">
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={this.sort('id')}>
                  ID <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('organizationName')}>
                  Organization Name <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('description')}>
                  Description <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('organisationAddress')}>
                  Organisation Address <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('organisationEmail')}>
                  Organisation Email <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('isActive')}>
                  Is Active <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('emailServerHost')}>
                  Email Server Host <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('emailServerPort')}>
                  Email Server Port <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('emailServerUserId')}>
                  Email Server User Id <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('emailServerPassword')}>
                  Email Server Password <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('isEmailServerAccessAllowed')}>
                  Is Email Server Access Allowed <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('createDate')}>
                  Create Date <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('createdBy')}>
                  Created By <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('updateDate')}>
                  Update Date <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('updatedBy')}>
                  Updated By <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {organizationsDetailsList.map((organizationsDetails, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${organizationsDetails.id}`} color="link" size="sm">
                      {organizationsDetails.id}
                    </Button>
                  </td>
                  <td>{organizationsDetails.organizationName}</td>
                  <td>{organizationsDetails.description}</td>
                  <td>{organizationsDetails.organisationAddress}</td>
                  <td>{organizationsDetails.organisationEmail}</td>
                  <td>{organizationsDetails.isActive}</td>
                  <td>{organizationsDetails.emailServerHost}</td>
                  <td>{organizationsDetails.emailServerPort}</td>
                  <td>{organizationsDetails.emailServerUserId}</td>
                  <td>{organizationsDetails.emailServerPassword}</td>
                  <td>{organizationsDetails.isEmailServerAccessAllowed}</td>
                  <td>
                    <TextFormat type="date" value={organizationsDetails.createDate} format={APP_DATE_FORMAT} />
                  </td>
                  <td>{organizationsDetails.createdBy}</td>
                  <td>
                    <TextFormat type="date" value={organizationsDetails.updateDate} format={APP_DATE_FORMAT} />
                  </td>
                  <td>{organizationsDetails.updatedBy}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${organizationsDetails.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${organizationsDetails.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${organizationsDetails.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        </div>
        <Row className="justify-content-center">
          <JhiPagination
            items={getPaginationItemsNumber(totalItems, this.state.itemsPerPage)}
            activePage={this.state.activePage}
            onSelect={this.handlePagination}
            maxButtons={5}
          />
        </Row>
      </div>
    );
  }
}

const mapStateToProps = ({ organizationsDetails }: IRootState) => ({
  organizationsDetailsList: organizationsDetails.entities,
  totalItems: organizationsDetails.totalItems
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(OrganizationsDetails);
