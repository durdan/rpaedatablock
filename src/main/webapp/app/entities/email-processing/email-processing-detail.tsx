import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './email-processing.reducer';
import { IEmailProcessing } from 'app/shared/model/email-processing.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IEmailProcessingDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export class EmailProcessingDetail extends React.Component<IEmailProcessingDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { emailProcessingEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            EmailProcessing [<b>{emailProcessingEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="messageId">Message Id</span>
            </dt>
            <dd>{emailProcessingEntity.messageId}</dd>
            <dt>
              <span id="receiveFrom">Receive From</span>
            </dt>
            <dd>{emailProcessingEntity.receiveFrom}</dd>
            <dt>
              <span id="receivedTime">Received Time</span>
            </dt>
            <dd>
              <TextFormat value={emailProcessingEntity.receivedTime} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="numberOfAttachments">Number Of Attachments</span>
            </dt>
            <dd>{emailProcessingEntity.numberOfAttachments}</dd>
            <dt>Client Email List</dt>
            <dd>{emailProcessingEntity.clientEmailListId ? emailProcessingEntity.clientEmailListId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/email-processing" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/email-processing/${emailProcessingEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ emailProcessing }: IRootState) => ({
  emailProcessingEntity: emailProcessing.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(EmailProcessingDetail);
