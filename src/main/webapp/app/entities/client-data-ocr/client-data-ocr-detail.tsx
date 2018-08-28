import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './client-data-ocr.reducer';
import { IClientDataOcr } from 'app/shared/model/client-data-ocr.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IClientDataOcrDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export class ClientDataOcrDetail extends React.Component<IClientDataOcrDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { clientDataOcrEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            ClientDataOcr [<b>{clientDataOcrEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="keyName">Key Name</span>
            </dt>
            <dd>{clientDataOcrEntity.keyName}</dd>
            <dt>
              <span id="value">Value</span>
            </dt>
            <dd>{clientDataOcrEntity.value}</dd>
            <dt>Transaction</dt>
            <dd>{clientDataOcrEntity.transactionId ? clientDataOcrEntity.transactionId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/client-data-ocr" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/client-data-ocr/${clientDataOcrEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ clientDataOcr }: IRootState) => ({
  clientDataOcrEntity: clientDataOcr.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ClientDataOcrDetail);
