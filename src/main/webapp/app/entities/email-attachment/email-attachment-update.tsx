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
import { IFileForOCRProcessing } from 'app/shared/model/file-for-ocr-processing.model';
import { getEntities as getFileForOcrProcessings } from 'app/entities/file-for-ocr-processing/file-for-ocr-processing.reducer';
import { getEntity, updateEntity, createEntity, reset } from './email-attachment.reducer';
import { IEmailAttachment } from 'app/shared/model/email-attachment.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IEmailAttachmentUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export interface IEmailAttachmentUpdateState {
  isNew: boolean;
  emailProcessingId: number;
  fileForOCRProcessingId: number;
}

export class EmailAttachmentUpdate extends React.Component<IEmailAttachmentUpdateProps, IEmailAttachmentUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      emailProcessingId: 0,
      fileForOCRProcessingId: 0,
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
    this.props.getFileForOcrProcessings();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { emailAttachmentEntity } = this.props;
      const entity = {
        ...emailAttachmentEntity,
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
    this.props.history.push('/entity/email-attachment');
  };

  render() {
    const { emailAttachmentEntity, emailProcessings, fileForOCRProcessings, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="rpaedatablockApp.emailAttachment.home.createOrEditLabel">Create or edit a EmailAttachment</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : emailAttachmentEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">ID</Label>
                    <AvInput id="email-attachment-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="fileNameLabel" for="fileName">
                    File Name
                  </Label>
                  <AvField id="email-attachment-fileName" type="text" name="fileName" />
                </AvGroup>
                <AvGroup>
                  <Label id="fileExtensionLabel" for="fileExtension">
                    File Extension
                  </Label>
                  <AvField id="email-attachment-fileExtension" type="text" name="fileExtension" />
                </AvGroup>
                <AvGroup>
                  <Label id="fileLocationLabel" for="fileLocation">
                    File Location
                  </Label>
                  <AvField id="email-attachment-fileLocation" type="text" name="fileLocation" />
                </AvGroup>
                <AvGroup>
                  <Label for="emailProcessing.id">Email Processing</Label>
                  <AvInput id="email-attachment-emailProcessing" type="select" className="form-control" name="emailProcessingId">
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
                <Button tag={Link} id="cancel-save" to="/entity/email-attachment" replace color="info">
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
  fileForOCRProcessings: storeState.fileForOCRProcessing.entities,
  emailAttachmentEntity: storeState.emailAttachment.entity,
  loading: storeState.emailAttachment.loading,
  updating: storeState.emailAttachment.updating
});

const mapDispatchToProps = {
  getEmailProcessings,
  getFileForOcrProcessings,
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
)(EmailAttachmentUpdate);
