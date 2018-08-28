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
import { getEntity, updateEntity, createEntity, reset } from './client-data-ocr.reducer';
import { IClientDataOcr } from 'app/shared/model/client-data-ocr.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IClientDataOcrUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export interface IClientDataOcrUpdateState {
  isNew: boolean;
  transactionId: number;
}

export class ClientDataOcrUpdate extends React.Component<IClientDataOcrUpdateProps, IClientDataOcrUpdateState> {
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
    if (errors.length === 0) {
      const { clientDataOcrEntity } = this.props;
      const entity = {
        ...clientDataOcrEntity,
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
    this.props.history.push('/entity/client-data-ocr');
  };

  render() {
    const { clientDataOcrEntity, transactions, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="rpaedatablockApp.clientDataOcr.home.createOrEditLabel">Create or edit a ClientDataOcr</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : clientDataOcrEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">ID</Label>
                    <AvInput id="client-data-ocr-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="keyNameLabel" for="keyName">
                    Key Name
                  </Label>
                  <AvField id="client-data-ocr-keyName" type="text" name="keyName" />
                </AvGroup>
                <AvGroup>
                  <Label id="valueLabel" for="value">
                    Value
                  </Label>
                  <AvField id="client-data-ocr-value" type="text" name="value" />
                </AvGroup>
                <AvGroup>
                  <Label for="transaction.id">Transaction</Label>
                  <AvInput id="client-data-ocr-transaction" type="select" className="form-control" name="transactionId">
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
                <Button tag={Link} id="cancel-save" to="/entity/client-data-ocr" replace color="info">
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
  clientDataOcrEntity: storeState.clientDataOcr.entity,
  loading: storeState.clientDataOcr.loading,
  updating: storeState.clientDataOcr.updating
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
)(ClientDataOcrUpdate);
