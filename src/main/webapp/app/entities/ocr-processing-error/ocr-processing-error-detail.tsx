import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './ocr-processing-error.reducer';
import { IOcrProcessingError } from 'app/shared/model/ocr-processing-error.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IOcrProcessingErrorDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export class OcrProcessingErrorDetail extends React.Component<IOcrProcessingErrorDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { ocrProcessingErrorEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            OcrProcessingError [<b>{ocrProcessingErrorEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="errorMessage">Error Message</span>
            </dt>
            <dd>{ocrProcessingErrorEntity.errorMessage}</dd>
            <dt>
              <span id="createdDateTime">Created Date Time</span>
            </dt>
            <dd>
              <TextFormat value={ocrProcessingErrorEntity.createdDateTime} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="errorType">Error Type</span>
            </dt>
            <dd>{ocrProcessingErrorEntity.errorType}</dd>
            <dt>Transaction</dt>
            <dd>{ocrProcessingErrorEntity.transactionId ? ocrProcessingErrorEntity.transactionId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/ocr-processing-error" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/ocr-processing-error/${ocrProcessingErrorEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ ocrProcessingError }: IRootState) => ({
  ocrProcessingErrorEntity: ocrProcessingError.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(OcrProcessingErrorDetail);
