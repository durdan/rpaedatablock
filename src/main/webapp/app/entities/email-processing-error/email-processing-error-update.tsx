import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IEmailProcessing } from 'app/shared/model/email-processing.model';
import { getEntities as getEmailProcessings } from 'app/entities/email-processing/email-processing.reducer';
import { getEntity, updateEntity, createEntity, reset } from './email-processing-error.reducer';
import { IEmailProcessingError } from 'app/shared/model/email-processing-error.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IEmailProcessingErrorUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export interface IEmailProcessingErrorUpdateState {
  isNew: boolean;
  emailProcessingId: number;
}

export class EmailProcessingErrorUpdate extends React.Component<IEmailProcessingErrorUpdateProps, IEmailProcessingErrorUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      emailProcessingId: 0,
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }

    this.props.getEmailProcessings();
  }

  saveEntity = (event, errors, values) => {
    values.receivedTime = new Date(values.receivedTime);

    if (errors.length === 0) {
      const { emailProcessingErrorEntity } = this.props;
      const entity = {
        ...emailProcessingErrorEntity,
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
    this.props.history.push('/entity/email-processing-error');
  };

  render() {
    const { emailProcessingErrorEntity, emailProcessings, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="rpaedatablockApp.emailProcessingError.home.createOrEditLabel">Create or edit a EmailProcessingError</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : emailProcessingErrorEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">ID</Label>
                    <AvInput id="email-processing-error-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="errorMessageLabel" for="errorMessage">
                    Error Message
                  </Label>
                  <AvField id="email-processing-error-errorMessage" type="text" name="errorMessage" />
                </AvGroup>
                <AvGroup>
                  <Label id="messageIDLabel" for="messageID">
                    Message ID
                  </Label>
                  <AvField id="email-processing-error-messageID" type="text" name="messageID" />
                </AvGroup>
                <AvGroup>
                  <Label id="receiveFromLabel" for="receiveFrom">
                    Receive From
                  </Label>
                  <AvField id="email-processing-error-receiveFrom" type="text" name="receiveFrom" />
                </AvGroup>
                <AvGroup>
                  <Label id="receivedTimeLabel" for="receivedTime">
                    Received Time
                  </Label>
                  <AvInput
                    id="email-processing-error-receivedTime"
                    type="datetime-local"
                    className="form-control"
                    name="receivedTime"
                    value={isNew ? null : convertDateTimeFromServer(this.props.emailProcessingErrorEntity.receivedTime)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label for="emailProcessing.id">Email Processing</Label>
                  <AvInput id="email-processing-error-emailProcessing" type="select" className="form-control" name="emailProcessingId">
                    <option value="" key="0" />
                    {emailProcessings
                      ? emailProcessings.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/email-processing-error" replace color="info">
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
  emailProcessings: storeState.emailProcessing.entities,
  emailProcessingErrorEntity: storeState.emailProcessingError.entity,
  loading: storeState.emailProcessingError.loading,
  updating: storeState.emailProcessingError.updating
});

const mapDispatchToProps = {
  getEmailProcessings,
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
)(EmailProcessingErrorUpdate);
