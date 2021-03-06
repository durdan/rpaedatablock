import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { ITransaction } from 'app/shared/model/transaction.model';
import { getEntities as getTransactions } from 'app/entities/transaction/transaction.reducer';
import { getEntity, updateEntity, createEntity, reset } from './ocr-processing-error.reducer';
import { IOcrProcessingError } from 'app/shared/model/ocr-processing-error.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IOcrProcessingErrorUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export interface IOcrProcessingErrorUpdateState {
  isNew: boolean;
  transactionId: number;
}

export class OcrProcessingErrorUpdate extends React.Component<IOcrProcessingErrorUpdateProps, IOcrProcessingErrorUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      transactionId: 0,
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }

    this.props.getTransactions();
  }

  saveEntity = (event, errors, values) => {
    values.createdDateTime = new Date(values.createdDateTime);

    if (errors.length === 0) {
      const { ocrProcessingErrorEntity } = this.props;
      const entity = {
        ...ocrProcessingErrorEntity,
        ...values
      };

      if (this.state.isNew) {
        this.props.createEntity(entity);
      } else {
        this.props.updateEntity(entity);
      }
      this.handleClose();
    }
  };

  handleClose = () => {
    this.props.history.push('/entity/ocr-processing-error');
  };

  render() {
    const { ocrProcessingErrorEntity, transactions, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="rpaedatablockApp.ocrProcessingError.home.createOrEditLabel">Create or edit a OcrProcessingError</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : ocrProcessingErrorEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">ID</Label>
                    <AvInput id="ocr-processing-error-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="errorMessageLabel" for="errorMessage">
                    Error Message
                  </Label>
                  <AvField id="ocr-processing-error-errorMessage" type="text" name="errorMessage" />
                </AvGroup>
                <AvGroup>
                  <Label id="createdDateTimeLabel" for="createdDateTime">
                    Created Date Time
                  </Label>
                  <AvInput
                    id="ocr-processing-error-createdDateTime"
                    type="datetime-local"
                    className="form-control"
                    name="createdDateTime"
                    value={isNew ? null : convertDateTimeFromServer(this.props.ocrProcessingErrorEntity.createdDateTime)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="errorTypeLabel" for="errorType">
                    Error Type
                  </Label>
                  <AvField id="ocr-processing-error-errorType" type="text" name="errorType" />
                </AvGroup>
                <AvGroup>
                  <Label for="transaction.id">Transaction</Label>
                  <AvInput id="ocr-processing-error-transaction" type="select" className="form-control" name="transactionId">
                    <option value="" key="0" />
                    {transactions
                      ? transactions.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/ocr-processing-error" replace color="info">
                  <FontAwesomeIcon icon="arrow-left" />&nbsp;
                  <span className="d-none d-md-inline">Back</span>
                </Button>
                &nbsp;
                <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                  <FontAwesomeIcon icon="save" />&nbsp; Save
                </Button>
              </AvForm>
            )}
          </Col>
        </Row>
      </div>
    );
  }
}

const mapStateToProps = (storeState: IRootState) => ({
  transactions: storeState.transaction.entities,
  ocrProcessingErrorEntity: storeState.ocrProcessingError.entity,
  loading: storeState.ocrProcessingError.loading,
  updating: storeState.ocrProcessingError.updating
});

const mapDispatchToProps = {
  getTransactions,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(OcrProcessingErrorUpdate);
