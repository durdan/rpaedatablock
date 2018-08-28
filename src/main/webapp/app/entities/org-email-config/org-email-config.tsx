import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAllAction, TextFormat, getSortState, IPaginationBaseState, getPaginationItemsNumber, JhiPagination } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './org-email-config.reducer';
import { IOrgEmailConfig } from 'app/shared/model/org-email-config.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface IOrgEmailConfigProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export type IOrgEmailConfigState = IPaginationBaseState;

export class OrgEmailConfig extends React.Component<IOrgEmailConfigProps, IOrgEmailConfigState> {
  state: IOrgEmailConfigState = {
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
    const { orgEmailConfigList, match, totalItems } = this.props;
    return (
      <div>
        <h2 id="org-email-config-heading">
          Org Email Configs
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp; Create new Org Email Config
          </Link>
        </h2>
        <div className="table-responsive">
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={this.sort('id')}>
                  ID <FontAwesomeIcon icon="sort" />
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
                <th>
                  Client <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {orgEmailConfigList.map((orgEmailConfig, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${orgEmailConfig.id}`} color="link" size="sm">
                      {orgEmailConfig.id}
                    </Button>
                  </td>
                  <td>{orgEmailConfig.emailServerHost}</td>
                  <td>{orgEmailConfig.emailServerPort}</td>
                  <td>{orgEmailConfig.emailServerUserId}</td>
                  <td>{orgEmailConfig.emailServerPassword}</td>
                  <td>
                    <TextFormat type="date" value={orgEmailConfig.createDate} format={APP_DATE_FORMAT} />
                  </td>
                  <td>{orgEmailConfig.createdBy}</td>
                  <td>
                    <TextFormat type="date" value={orgEmailConfig.updateDate} format={APP_DATE_FORMAT} />
                  </td>
                  <td>{orgEmailConfig.updatedBy}</td>
                  <td>
                    {orgEmailConfig.clientId ? (
                      <Link to={`organizations-details/${orgEmailConfig.clientId}`}>{orgEmailConfig.clientId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${orgEmailConfig.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${orgEmailConfig.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${orgEmailConfig.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ orgEmailConfig }: IRootState) => ({
  orgEmailConfigList: orgEmailConfig.entities,
  totalItems: orgEmailConfig.totalItems
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(OrgEmailConfig);
