import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './client-email-list.reducer';
import { IClientEmailList } from 'app/shared/model/client-email-list.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IClientEmailListDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export class ClientEmailListDetail extends React.Component<IClientEmailListDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { clientEmailListEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            ClientEmailList [<b>{clientEmailListEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="emailAddress">Email Address</span>
            </dt>
            <dd>{clientEmailListEntity.emailAddress}</dd>
            <dt>
              <span id="description">Description</span>
            </dt>
            <dd>{clientEmailListEntity.description}</dd>
            <dt>
              <span id="isActive">Is Active</span>
            </dt>
            <dd>{clientEmailListEntity.isActive}</dd>
            <dt>Client</dt>
            <dd>{clientEmailListEntity.clientId ? clientEmailListEntity.clientId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/client-email-list" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/client-email-list/${clientEmailListEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ clientEmailList }: IRootState) => ({
  clientEmailListEntity: clientEmailList.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ClientEmailListDetail);
