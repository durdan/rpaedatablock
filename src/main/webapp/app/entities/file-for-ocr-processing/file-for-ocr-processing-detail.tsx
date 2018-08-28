import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './file-for-ocr-processing.reducer';
import { IFileForOCRProcessing } from 'app/shared/model/file-for-ocr-processing.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IFileForOCRProcessingDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export class FileForOCRProcessingDetail extends React.Component<IFileForOCRProcessingDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { fileForOCRProcessingEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            FileForOCRProcessing [<b>{fileForOCRProcessingEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="fileInputType">File Input Type</span>
            </dt>
            <dd>{fileForOCRProcessingEntity.fileInputType}</dd>
            <dt>
              <span id="status">Status</span>
            </dt>
            <dd>{fileForOCRProcessingEntity.status}</dd>
            <dt>
              <span id="retry">Retry</span>
            </dt>
            <dd>{fileForOCRProcessingEntity.retry}</dd>
            <dt>
              <span id="createdDateTime">Created Date Time</span>
            </dt>
            <dd>
              <TextFormat value={fileForOCRProcessingEntity.createdDateTime} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="createdBy">Created By</span>
            </dt>
            <dd>{fileForOCRProcessingEntity.createdBy}</dd>
            <dt>
              <span id="updateTimeStamp">Update Time Stamp</span>
            </dt>
            <dd>
              <TextFormat value={fileForOCRProcessingEntity.updateTimeStamp} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="updateBy">Update By</span>
            </dt>
            <dd>
              <TextFormat value={fileForOCRProcessingEntity.updateBy} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>Email Attachment</dt>
            <dd>{fileForOCRProcessingEntity.emailAttachmentId ? fileForOCRProcessingEntity.emailAttachmentId : ''}</dd>
            <dt>Upload Files</dt>
            <dd>{fileForOCRProcessingEntity.uploadFilesId ? fileForOCRProcessingEntity.uploadFilesId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/file-for-ocr-processing" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/file-for-ocr-processing/${fileForOCRProcessingEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ fileForOCRProcessing }: IRootState) => ({
  fileForOCRProcessingEntity: fileForOCRProcessing.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(FileForOCRProcessingDetail);
