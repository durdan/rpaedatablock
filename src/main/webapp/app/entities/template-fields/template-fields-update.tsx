import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { ITemplateDetails } from 'app/shared/model/template-details.model';
import { getEntities as getTemplateDetails } from 'app/entities/template-details/template-details.reducer';
import { getEntity, updateEntity, createEntity, reset } from './template-fields.reducer';
import { ITemplateFields } from 'app/shared/model/template-fields.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ITemplateFieldsUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export interface ITemplateFieldsUpdateState {
  isNew: boolean;
  templateDetailsId: number;
}

export class TemplateFieldsUpdate extends React.Component<ITemplateFieldsUpdateProps, ITemplateFieldsUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      templateDetailsId: 0,
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }

    this.props.getTemplateDetails();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { templateFieldsEntity } = this.props;
      const entity = {
        ...templateFieldsEntity,
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
    this.props.history.push('/entity/template-fields');
  };

  render() {
    const { templateFieldsEntity, templateDetails, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="rpaedatablockApp.templateFields.home.createOrEditLabel">Create or edit a TemplateFields</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : templateFieldsEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">ID</Label>
                    <AvInput id="template-fields-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="fieldNameLabel" for="fieldName">
                    Field Name
                  </Label>
                  <AvField id="template-fields-fieldName" type="text" name="fieldName" />
                </AvGroup>
                <AvGroup>
                  <Label id="fieldZoneMinXLabel" for="fieldZoneMinX">
                    Field Zone Min X
                  </Label>
                  <AvField id="template-fields-fieldZoneMinX" type="number" className="form-control" name="fieldZoneMinX" />
                </AvGroup>
                <AvGroup>
                  <Label id="fieldZoneMinYLabel" for="fieldZoneMinY">
                    Field Zone Min Y
                  </Label>
                  <AvField id="template-fields-fieldZoneMinY" type="number" className="form-control" name="fieldZoneMinY" />
                </AvGroup>
                <AvGroup>
                  <Label id="fieldZoneMaxXLabel" for="fieldZoneMaxX">
                    Field Zone Max X
                  </Label>
                  <AvField id="template-fields-fieldZoneMaxX" type="number" className="form-control" name="fieldZoneMaxX" />
                </AvGroup>
                <AvGroup>
                  <Label id="fieldZoneMaxYLabel" for="fieldZoneMaxY">
                    Field Zone Max Y
                  </Label>
                  <AvField id="template-fields-fieldZoneMaxY" type="number" className="form-control" name="fieldZoneMaxY" />
                </AvGroup>
                <AvGroup>
                  <Label id="fieldValidationRequireLabel" for="fieldValidationRequire">
                    Field Validation Require
                  </Label>
                  <AvField
                    id="template-fields-fieldValidationRequire"
                    type="number"
                    className="form-control"
                    name="fieldValidationRequire"
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="fieldValidationRuleLabel" for="fieldValidationRule">
                    Field Validation Rule
                  </Label>
                  <AvField id="template-fields-fieldValidationRule" type="text" name="fieldValidationRule" />
                </AvGroup>
                <AvGroup>
                  <Label id="fieldLocationLabel" for="fieldLocation">
                    Field Location
                  </Label>
                  <AvField id="template-fields-fieldLocation" type="number" className="form-control" name="fieldLocation" />
                </AvGroup>
                <AvGroup>
                  <Label for="templateDetails.id">Template Details</Label>
                  <AvInput id="template-fields-templateDetails" type="select" className="form-control" name="templateDetailsId">
                    <option value="" key="0" />
                    {templateDetails
                      ? templateDetails.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/template-fields" replace color="info">
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
  templateDetails: storeState.templateDetails.entities,
  templateFieldsEntity: storeState.templateFields.entity,
  loading: storeState.templateFields.loading,
  updating: storeState.templateFields.updating
});

const mapDispatchToProps = {
  getTemplateDetails,
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
)(TemplateFieldsUpdate);
