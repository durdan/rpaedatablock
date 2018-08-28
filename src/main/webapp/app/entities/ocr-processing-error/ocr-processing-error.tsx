import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAllAction, TextFormat, getSortState, IPaginationBaseState, getPaginationItemsNumber, JhiPagination } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './ocr-processing-error.reducer';
import { IOcrProcessingError } from 'app/shared/model/ocr-processing-error.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface IOcrProcessingErrorProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export type IOcrProcessingErrorState = IPaginationBaseState;

export class OcrProcessingError extends React.Component<IOcrProcessingErrorProps, IOcrProcessingErrorState> {
  state: IOcrProcessingErrorState = {
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
    const { ocrProcessingErrorList, match, totalItems } = this.props;
    return (
      <div>
        <h2 id="ocr-processing-error-heading">
          Ocr Processing Errors
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp; Create new Ocr Processing Error
          </Link>
        </h2>
        <div className="table-responsive">
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={this.sort('id')}>
                  ID <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('errorMessage')}>
                  Error Message <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('createdDateTime')}>
                  Created Date Time <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('errorType')}>
                  Error Type <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  Transaction <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {ocrProcessingErrorList.map((ocrProcessingError, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${ocrProcessingError.id}`} color="link" size="sm">
                      {ocrProcessingError.id}
                    </Button>
                  </td>
                  <td>{ocrProcessingError.errorMessage}</td>
                  <td>
                    <TextFormat type="date" value={ocrProcessingError.createdDateTime} format={APP_DATE_FORMAT} />
                  </td>
                  <td>{ocrProcessingError.errorType}</td>
                  <td>
                    {ocrProcessingError.transactionId ? (
                      <Link to={`transaction/${ocrProcessingError.transactionId}`}>{ocrProcessingError.transactionId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${ocrProcessingError.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${ocrProcessingError.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${ocrProcessingError.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ ocrProcessingError }: IRootState) => ({
  ocrProcessingErrorList: ocrProcessingError.entities,
  totalItems: ocrProcessingError.totalItems
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(OcrProcessingError);
