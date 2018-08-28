import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './template-details.reducer';
import { ITemplateDetails } from 'app/shared/model/template-details.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ITemplateDetailsDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export class TemplateDetailsDetail extends React.Component<ITemplateDetailsDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { templateDetailsEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            TemplateDetails [<b>{templateDetailsEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="templateName">Template Name</span>
            </dt>
            <dd>{templateDetailsEntity.templateName}</dd>
            <dt>
              <span id="templateDescription">Template Description</span>
            </dt>
            <dd>{templateDetailsEntity.templateDescription}</dd>
            <dt>
              <span id="templateType">Template Type</span>
            </dt>
            <dd>{templateDetailsEntity.templateType}</dd>
            <dt>
              <span id="isActive">Is Active</span>
            </dt>
            <dd>{templateDetailsEntity.isActive}</dd>
            <dt>
              <span id="createDate">Create Date</span>
            </dt>
            <dd>
              <TextFormat value={templateDetailsEntity.createDate} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="createdBy">Created By</span>
            </dt>
            <dd>{templateDetailsEntity.createdBy}</dd>
            <dt>
              <span id="updateDate">Update Date</span>
            </dt>
            <dd>
              <TextFormat value={templateDetailsEntity.updateDate} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="updatedBy">Updated By</span>
            </dt>
            <dd>{templateDetailsEntity.updatedBy}</dd>
            <dt>Client</dt>
            <dd>{templateDetailsEntity.clientId ? templateDetailsEntity.clientId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/template-details" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/template-details/${templateDetailsEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ templateDetails }: IRootState) => ({
  templateDetailsEntity: templateDetails.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(TemplateDetailsDetail);
