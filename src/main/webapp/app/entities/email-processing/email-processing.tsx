import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAllAction, TextFormat, getSortState, IPaginationBaseState, getPaginationItemsNumber, JhiPagination } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './email-processing.reducer';
import { IEmailProcessing } from 'app/shared/model/email-processing.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface IEmailProcessingProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export type IEmailProcessingState = IPaginationBaseState;

export class EmailProcessing extends React.Component<IEmailProcessingProps, IEmailProcessingState> {
  state: IEmailProcessingState = {
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
    const { emailProcessingList, match, totalItems } = this.props;
    return (
      <div>
        <h2 id="email-processing-heading">
          Email Processings
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp; Create new Email Processing
          </Link>
        </h2>
        <div className="table-responsive">
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={this.sort('id')}>
                  ID <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('messageId')}>
                  Message Id <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('receiveFrom')}>
                  Receive From <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('receivedTime')}>
                  Received Time <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('numberOfAttachments')}>
                  Number Of Attachments <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  Client Email List <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {emailProcessingList.map((emailProcessing, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${emailProcessing.id}`} color="link" size="sm">
                      {emailProcessing.id}
                    </Button>
                  </td>
                  <td>{emailProcessing.messageId}</td>
                  <td>{emailProcessing.receiveFrom}</td>
                  <td>
                    <TextFormat type="date" value={emailProcessing.receivedTime} format={APP_DATE_FORMAT} />
                  </td>
                  <td>{emailProcessing.numberOfAttachments}</td>
                  <td>
                    {emailProcessing.clientEmailListId ? (
                      <Link to={`client-email-list/${emailProcessing.clientEmailListId}`}>{emailProcessing.clientEmailListId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${emailProcessing.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${emailProcessing.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${emailProcessing.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ emailProcessing }: IRootState) => ({
  emailProcessingList: emailProcessing.entities,
  totalItems: emailProcessing.totalItems
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(EmailProcessing);
