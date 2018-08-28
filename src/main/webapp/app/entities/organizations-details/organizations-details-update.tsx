import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './organizations-details.reducer';
import { IOrganizationsDetails } from 'app/shared/model/organizations-details.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IOrganizationsDetailsUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export interface IOrganizationsDetailsUpdateState {
  isNew: boolean;
}

export class OrganizationsDetailsUpdate extends React.Component<IOrganizationsDetailsUpdateProps, IOrganizationsDetailsUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }
  }

  saveEntity = (event, errors, values) => {
    values.createDate = new Date(values.createDate);
    values.updateDate = new Date(values.updateDate);

    if (errors.length === 0) {
      const { organizationsDetailsEntity } = this.props;
      const entity = {
        ...organizationsDetailsEntity,
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
    this.props.history.push('/entity/organizations-details');
  };

  render() {
    const { organizationsDetailsEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="rpaedatablockApp.organizationsDetails.home.createOrEditLabel">Create or edit a OrganizationsDetails</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : organizationsDetailsEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">ID</Label>
                    <AvInput id="organizations-details-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="organizationNameLabel" for="organizationName">
                    Organization Name
                  </Label>
                  <AvField id="organizations-details-organizationName" type="text" name="organizationName" />
                </AvGroup>
                <AvGroup>
                  <Label id="descriptionLabel" for="description">
                    Description
                  </Label>
                  <AvField id="organizations-details-description" type="text" name="description" />
                </AvGroup>
                <AvGroup>
                  <Label id="organisationAddressLabel" for="organisationAddress">
                    Organisation Address
                  </Label>
                  <AvField id="organizations-details-organisationAddress" type="text" name="organisationAddress" />
                </AvGroup>
                <AvGroup>
                  <Label id="organisationEmailLabel" for="organisationEmail">
                    Organisation Email
                  </Label>
                  <AvField id="organizations-details-organisationEmail" type="text" name="organisationEmail" />
                </AvGroup>
                <AvGroup>
                  <Label id="isActiveLabel" for="isActive">
                    Is Active
                  </Label>
                  <AvField id="organizations-details-isActive" type="number" className="form-control" name="isActive" />
                </AvGroup>
                <AvGroup>
                  <Label id="emailServerHostLabel" for="emailServerHost">
                    Email Server Host
                  </Label>
                  <AvField id="organizations-details-emailServerHost" type="text" name="emailServerHost" />
                </AvGroup>
                <AvGroup>
                  <Label id="emailServerPortLabel" for="emailServerPort">
                    Email Server Port
                  </Label>
                  <AvField id="organizations-details-emailServerPort" type="number" className="form-control" name="emailServerPort" />
                </AvGroup>
                <AvGroup>
                  <Label id="emailServerUserIdLabel" for="emailServerUserId">
                    Email Server User Id
                  </Label>
                  <AvField id="organizations-details-emailServerUserId" type="text" name="emailServerUserId" />
                </AvGroup>
                <AvGroup>
                  <Label id="emailServerPasswordLabel" for="emailServerPassword">
                    Email Server Password
                  </Label>
                  <AvField id="organizations-details-emailServerPassword" type="text" name="emailServerPassword" />
                </AvGroup>
                <AvGroup>
                  <Label id="isEmailServerAccessAllowedLabel" for="isEmailServerAccessAllowed">
                    Is Email Server Access Allowed
                  </Label>
                  <AvField
                    id="organizations-details-isEmailServerAccessAllowed"
                    type="number"
                    className="form-control"
                    name="isEmailServerAccessAllowed"
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="createDateLabel" for="createDate">
                    Create Date
                  </Label>
                  <AvInput
                    id="organizations-details-createDate"
                    type="datetime-local"
                    className="form-control"
                    name="createDate"
                    value={isNew ? null : convertDateTimeFromServer(this.props.organizationsDetailsEntity.createDate)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="createdByLabel" for="createdBy">
                    Created By
                  </Label>
                  <AvField id="organizations-details-createdBy" type="text" name="createdBy" />
                </AvGroup>
                <AvGroup>
                  <Label id="updateDateLabel" for="updateDate">
                    Update Date
                  </Label>
                  <AvInput
                    id="organizations-details-updateDate"
                    type="datetime-local"
                    className="form-control"
                    name="updateDate"
                    value={isNew ? null : convertDateTimeFromServer(this.props.organizationsDetailsEntity.updateDate)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="updatedByLabel" for="updatedBy">
                    Updated By
                  </Label>
                  <AvField id="organizations-details-updatedBy" type="text" name="updatedBy" />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/organizations-details" replace color="info">
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
  organizationsDetailsEntity: storeState.organizationsDetails.entity,
  loading: storeState.organizationsDetails.loading,
  updating: storeState.organizationsDetails.updating
});

const mapDispatchToProps = {
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
)(OrganizationsDetailsUpdate);
