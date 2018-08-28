import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './email-processing-error.reducer';
import { IEmailProcessingError } from 'app/shared/model/email-processing-error.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IEmailProcessingErrorDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export class EmailProcessingErrorDetail extends React.Component<IEmailProcessingErrorDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { emailProcessingErrorEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            EmailProcessingError [<b>{emailProcessingErrorEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="errorMessage">Error Message</span>
            </dt>
            <dd>{emailProcessingErrorEntity.errorMessage}</dd>
            <dt>
              <span id="messageID">Message ID</span>
            </dt>
            <dd>{emailProcessingErrorEntity.messageID}</dd>
            <dt>
              <span id="receiveFrom">Receive From</span>
            </dt>
            <dd>{emailProcessingErrorEntity.receiveFrom}</dd>
            <dt>
              <span id="receivedTime">Received Time</span>
            </dt>
            <dd>
              <TextFormat value={emailProcessingErrorEntity.receivedTime} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>Email Processing</dt>
            <dd>{emailProcessingErrorEntity.emailProcessingId ? emailProcessingErrorEntity.emailProcessingId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/email-processing-error" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/email-processing-error/${emailProcessingErrorEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ emailProcessingError }: IRootState) => ({
  emailProcessingErrorEntity: emailProcessingError.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(EmailProcessingErrorDetail);
