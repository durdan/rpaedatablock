import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IEmailProcessing, defaultValue } from 'app/shared/model/email-processing.model';

export const ACTION_TYPES = {
  FETCH_EMAILPROCESSING_LIST: 'emailProcessing/FETCH_EMAILPROCESSING_LIST',
  FETCH_EMAILPROCESSING: 'emailProcessing/FETCH_EMAILPROCESSING',
  CREATE_EMAILPROCESSING: 'emailProcessing/CREATE_EMAILPROCESSING',
  UPDATE_EMAILPROCESSING: 'emailProcessing/UPDATE_EMAILPROCESSING',
  DELETE_EMAILPROCESSING: 'emailProcessing/DELETE_EMAILPROCESSING',
  RESET: 'emailProcessing/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IEmailProcessing>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type EmailProcessingState = Readonly<typeof initialState>;

// Reducer

export default (state: EmailProcessingState = initialState, action): EmailProcessingState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_EMAILPROCESSING_LIST):
    case REQUEST(ACTION_TYPES.FETCH_EMAILPROCESSING):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_EMAILPROCESSING):
    case REQUEST(ACTION_TYPES.UPDATE_EMAILPROCESSING):
    case REQUEST(ACTION_TYPES.DELETE_EMAILPROCESSING):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_EMAILPROCESSING_LIST):
    case FAILURE(ACTION_TYPES.FETCH_EMAILPROCESSING):
    case FAILURE(ACTION_TYPES.CREATE_EMAILPROCESSING):
    case FAILURE(ACTION_TYPES.UPDATE_EMAILPROCESSING):
    case FAILURE(ACTION_TYPES.DELETE_EMAILPROCESSING):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_EMAILPROCESSING_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_EMAILPROCESSING):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_EMAILPROCESSING):
    case SUCCESS(ACTION_TYPES.UPDATE_EMAILPROCESSING):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_EMAILPROCESSING):
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

const apiUrl = 'api/email-processings';

// Actions

export const getEntities: ICrudGetAllAction<IEmailProcessing> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_EMAILPROCESSING_LIST,
    payload: axios.get<IEmailProcessing>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IEmailProcessing> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_EMAILPROCESSING,
    payload: axios.get<IEmailProcessing>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IEmailProcessing> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_EMAILPROCESSING,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IEmailProcessing> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_EMAILPROCESSING,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IEmailProcessing> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_EMAILPROCESSING,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
