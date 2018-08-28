import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IOrganizationsDetails, defaultValue } from 'app/shared/model/organizations-details.model';

export const ACTION_TYPES = {
  FETCH_ORGANIZATIONSDETAILS_LIST: 'organizationsDetails/FETCH_ORGANIZATIONSDETAILS_LIST',
  FETCH_ORGANIZATIONSDETAILS: 'organizationsDetails/FETCH_ORGANIZATIONSDETAILS',
  CREATE_ORGANIZATIONSDETAILS: 'organizationsDetails/CREATE_ORGANIZATIONSDETAILS',
  UPDATE_ORGANIZATIONSDETAILS: 'organizationsDetails/UPDATE_ORGANIZATIONSDETAILS',
  DELETE_ORGANIZATIONSDETAILS: 'organizationsDetails/DELETE_ORGANIZATIONSDETAILS',
  RESET: 'organizationsDetails/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IOrganizationsDetails>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type OrganizationsDetailsState = Readonly<typeof initialState>;

// Reducer

export default (state: OrganizationsDetailsState = initialState, action): OrganizationsDetailsState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_ORGANIZATIONSDETAILS_LIST):
    case REQUEST(ACTION_TYPES.FETCH_ORGANIZATIONSDETAILS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_ORGANIZATIONSDETAILS):
    case REQUEST(ACTION_TYPES.UPDATE_ORGANIZATIONSDETAILS):
    case REQUEST(ACTION_TYPES.DELETE_ORGANIZATIONSDETAILS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_ORGANIZATIONSDETAILS_LIST):
    case FAILURE(ACTION_TYPES.FETCH_ORGANIZATIONSDETAILS):
    case FAILURE(ACTION_TYPES.CREATE_ORGANIZATIONSDETAILS):
    case FAILURE(ACTION_TYPES.UPDATE_ORGANIZATIONSDETAILS):
    case FAILURE(ACTION_TYPES.DELETE_ORGANIZATIONSDETAILS):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_ORGANIZATIONSDETAILS_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_ORGANIZATIONSDETAILS):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_ORGANIZATIONSDETAILS):
    case SUCCESS(ACTION_TYPES.UPDATE_ORGANIZATIONSDETAILS):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_ORGANIZATIONSDETAILS):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {}
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState
      };
    default:
      return state;
  }
};

const apiUrl = 'api/organizations-details';

// Actions

export const getEntities: ICrudGetAllAction<IOrganizationsDetails> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_ORGANIZATIONSDETAILS_LIST,
    payload: axios.get<IOrganizationsDetails>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IOrganizationsDetails> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_ORGANIZATIONSDETAILS,
    payload: axios.get<IOrganizationsDetails>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IOrganizationsDetails> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_ORGANIZATIONSDETAILS,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IOrganizationsDetails> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_ORGANIZATIONSDETAILS,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IOrganizationsDetails> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_ORGANIZATIONSDETAILS,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
