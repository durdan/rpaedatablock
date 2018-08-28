import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './organizations-details.reducer';
import { IOrganizationsDetails } from 'app/shared/model/organizations-details.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IOrganizationsDetailsDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export class OrganizationsDetailsDetail extends React.Component<IOrganizationsDetailsDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { organizationsDetailsEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            OrganizationsDetails [<b>{organizationsDetailsEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="organizationName">Organization Name</span>
            </dt>
            <dd>{organizationsDetailsEntity.organizationName}</dd>
            <dt>
              <span id="description">Description</span>
            </dt>
            <dd>{organizationsDetailsEntity.description}</dd>
            <dt>
              <span id="organisationAddress">Organisation Address</span>
            </dt>
            <dd>{organizationsDetailsEntity.organisationAddress}</dd>
            <dt>
              <span id="organisationEmail">Organisation Email</span>
            </dt>
            <dd>{organizationsDetailsEntity.organisationEmail}</dd>
            <dt>
              <span id="isActive">Is Active</span>
            </dt>
            <dd>{organizationsDetailsEntity.isActive}</dd>
            <dt>
              <span id="emailServerHost">Email Server Host</span>
            </dt>
            <dd>{organizationsDetailsEntity.emailServerHost}</dd>
            <dt>
              <span id="emailServerPort">Email Server Port</span>
            </dt>
            <dd>{organizationsDetailsEntity.emailServerPort}</dd>
            <dt>
              <span id="emailServerUserId">Email Server User Id</span>
            </dt>
            <dd>{organizationsDetailsEntity.emailServerUserId}</dd>
            <dt>
              <span id="emailServerPassword">Email Server Password</span>
            </dt>
            <dd>{organizationsDetailsEntity.emailServerPassword}</dd>
            <dt>
              <span id="isEmailServerAccessAllowed">Is Email Server Access Allowed</span>
            </dt>
            <dd>{organizationsDetailsEntity.isEmailServerAccessAllowed}</dd>
            <dt>
              <span id="createDate">Create Date</span>
            </dt>
            <dd>
              <TextFormat value={organizationsDetailsEntity.createDate} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="createdBy">Created By</span>
            </dt>
            <dd>{organizationsDetailsEntity.createdBy}</dd>
            <dt>
              <span id="updateDate">Update Date</span>
            </dt>
            <dd>
              <TextFormat value={organizationsDetailsEntity.updateDate} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="updatedBy">Updated By</span>
            </dt>
            <dd>{organizationsDetailsEntity.updatedBy}</dd>
          </dl>
          <Button tag={Link} to="/entity/organizations-details" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/organizations-details/${organizationsDetailsEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ organizationsDetails }: IRootState) => ({
  organizationsDetailsEntity: organizationsDetails.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(OrganizationsDetailsDetail);
