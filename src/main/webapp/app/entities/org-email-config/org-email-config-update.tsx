import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IOrganizationsDetails } from 'app/shared/model/organizations-details.model';
import { getEntities as getOrganizationsDetails } from 'app/entities/organizations-details/organizations-details.reducer';
import { getEntity, updateEntity, createEntity, reset } from './org-email-config.reducer';
import { IOrgEmailConfig } from 'app/shared/model/org-email-config.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IOrgEmailConfigUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export interface IOrgEmailConfigUpdateState {
  isNew: boolean;
  clientId: number;
}

export class OrgEmailConfigUpdate extends React.Component<IOrgEmailConfigUpdateProps, IOrgEmailConfigUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      clientId: 0,
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }

    this.props.getOrganizationsDetails();
  }

  saveEntity = (event, errors, values) => {
    values.createDate = new Date(values.createDate);
    values.updateDate = new Date(values.updateDate);

    if (errors.length === 0) {
      const { orgEmailConfigEntity } = this.props;
      const entity = {
        ...orgEmailConfigEntity,
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
    this.props.history.push('/entity/org-email-config');
  };

  render() {
    const { orgEmailConfigEntity, organizationsDetails, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="rpaedatablockApp.orgEmailConfig.home.createOrEditLabel">Create or edit a OrgEmailConfig</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : orgEmailConfigEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">ID</Label>
                    <AvInput id="org-email-config-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="emailServerHostLabel" for="emailServerHost">
                    Email Server Host
                  </Label>
                  <AvField id="org-email-config-emailServerHost" type="text" name="emailServerHost" />
                </AvGroup>
                <AvGroup>
                  <Label id="emailServerPortLabel" for="emailServerPort">
                    Email Server Port
                  </Label>
                  <AvField id="org-email-config-emailServerPort" type="number" className="form-control" name="emailServerPort" />
                </AvGroup>
                <AvGroup>
                  <Label id="emailServerUserIdLabel" for="emailServerUserId">
                    Email Server User Id
                  </Label>
                  <AvField id="org-email-config-emailServerUserId" type="text" name="emailServerUserId" />
                </AvGroup>
                <AvGroup>
                  <Label id="emailServerPasswordLabel" for="emailServerPassword">
                    Email Server Password
                  </Label>
                  <AvField id="org-email-config-emailServerPassword" type="text" name="emailServerPassword" />
                </AvGroup>
                <AvGroup>
                  <Label id="createDateLabel" for="createDate">
                    Create Date
                  </Label>
                  <AvInput
                    id="org-email-config-createDate"
                    type="datetime-local"
                    className="form-control"
                    name="createDate"
                    value={isNew ? null : convertDateTimeFromServer(this.props.orgEmailConfigEntity.createDate)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="createdByLabel" for="createdBy">
                    Created By
                  </Label>
                  <AvField id="org-email-config-createdBy" type="text" name="createdBy" />
                </AvGroup>
                <AvGroup>
                  <Label id="updateDateLabel" for="updateDate">
                    Update Date
                  </Label>
                  <AvInput
                    id="org-email-config-updateDate"
                    type="datetime-local"
                    className="form-control"
                    name="updateDate"
                    value={isNew ? null : convertDateTimeFromServer(this.props.orgEmailConfigEntity.updateDate)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="updatedByLabel" for="updatedBy">
                    Updated By
                  </Label>
                  <AvField id="org-email-config-updatedBy" type="text" name="updatedBy" />
                </AvGroup>
                <AvGroup>
                  <Label for="client.id">Client</Label>
                  <AvInput id="org-email-config-client" type="select" className="form-control" name="clientId">
                    <option value="" key="0" />
                    {organizationsDetails
                      ? organizationsDetails.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/org-email-config" replace color="info">
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
  organizationsDetails: storeState.organizationsDetails.entities,
  orgEmailConfigEntity: storeState.orgEmailConfig.entity,
  loading: storeState.orgEmailConfig.loading,
  updating: storeState.orgEmailConfig.updating
});

const mapDispatchToProps = {
  getOrganizationsDetails,
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
)(OrgEmailConfigUpdate);
