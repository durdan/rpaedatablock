import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IClientEmailList } from 'app/shared/model/client-email-list.model';
import { getEntities as getClientEmailLists } from 'app/entities/client-email-list/client-email-list.reducer';
import { IEmailProcessingError } from 'app/shared/model/email-processing-error.model';
import { getEntities as getEmailProcessingErrors } from 'app/entities/email-processing-error/email-processing-error.reducer';
import { getEntity, updateEntity, createEntity, reset } from './email-processing.reducer';
import { IEmailProcessing } from 'app/shared/model/email-processing.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IEmailProcessingUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export interface IEmailProcessingUpdateState {
  isNew: boolean;
  clientEmailListId: number;
  emailProcessingErrorId: number;
}

export class EmailProcessingUpdate extends React.Component<IEmailProcessingUpdateProps, IEmailProcessingUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      clientEmailListId: 0,
      emailProcessingErrorId: 0,
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }

    this.props.getClientEmailLists();
    this.props.getEmailProcessingErrors();
  }

  saveEntity = (event, errors, values) => {
    values.receivedTime = new Date(values.receivedTime);

    if (errors.length === 0) {
      const { emailProcessingEntity } = this.props;
      const entity = {
        ...emailProcessingEntity,
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
    this.props.history.push('/entity/email-processing');
  };

  render() {
    const { emailProcessingEntity, clientEmailLists, emailProcessingErrors, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="rpaedatablockApp.emailProcessing.home.createOrEditLabel">Create or edit a EmailProcessing</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : emailProcessingEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">ID</Label>
                    <AvInput id="email-processing-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="messageIdLabel" for="messageId">
                    Message Id
                  </Label>
                  <AvField id="email-processing-messageId" type="text" name="messageId" />
                </AvGroup>
                <AvGroup>
                  <Label id="receiveFromLabel" for="receiveFrom">
                    Receive From
                  </Label>
                  <AvField id="email-processing-receiveFrom" type="text" name="receiveFrom" />
                </AvGroup>
                <AvGroup>
                  <Label id="receivedTimeLabel" for="receivedTime">
                    Received Time
                  </Label>
                  <AvInput
                    id="email-processing-receivedTime"
                    type="datetime-local"
                    className="form-control"
                    name="receivedTime"
                    value={isNew ? null : convertDateTimeFromServer(this.props.emailProcessingEntity.receivedTime)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="numberOfAttachmentsLabel" for="numberOfAttachments">
                    Number Of Attachments
                  </Label>
                  <AvField id="email-processing-numberOfAttachments" type="text" name="numberOfAttachments" />
                </AvGroup>
                <AvGroup>
                  <Label for="clientEmailList.id">Client Email List</Label>
                  <AvInput id="email-processing-clientEmailList" type="select" className="form-control" name="clientEmailListId">
                    <option value="" key="0" />
                    {clientEmailLists
                      ? clientEmailLists.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/email-processing" replace color="info">
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
  clientEmailLists: storeState.clientEmailList.entities,
  emailProcessingErrors: storeState.emailProcessingError.entities,
  emailProcessingEntity: storeState.emailProcessing.entity,
  loading: storeState.emailProcessing.loading,
  updating: storeState.emailProcessing.updating
});

const mapDispatchToProps = {
  getClientEmailLists,
  getEmailProcessingErrors,
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
)(EmailProcessingUpdate);
