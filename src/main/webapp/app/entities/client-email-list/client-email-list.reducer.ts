import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IClientEmailList, defaultValue } from 'app/shared/model/client-email-list.model';

export const ACTION_TYPES = {
  FETCH_CLIENTEMAILLIST_LIST: 'clientEmailList/FETCH_CLIENTEMAILLIST_LIST',
  FETCH_CLIENTEMAILLIST: 'clientEmailList/FETCH_CLIENTEMAILLIST',
  CREATE_CLIENTEMAILLIST: 'clientEmailList/CREATE_CLIENTEMAILLIST',
  UPDATE_CLIENTEMAILLIST: 'clientEmailList/UPDATE_CLIENTEMAILLIST',
  DELETE_CLIENTEMAILLIST: 'clientEmailList/DELETE_CLIENTEMAILLIST',
  RESET: 'clientEmailList/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IClientEmailList>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type ClientEmailListState = Readonly<typeof initialState>;

// Reducer

export default (state: ClientEmailListState = initialState, action): ClientEmailListState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_CLIENTEMAILLIST_LIST):
    case REQUEST(ACTION_TYPES.FETCH_CLIENTEMAILLIST):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_CLIENTEMAILLIST):
    case REQUEST(ACTION_TYPES.UPDATE_CLIENTEMAILLIST):
    case REQUEST(ACTION_TYPES.DELETE_CLIENTEMAILLIST):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_CLIENTEMAILLIST_LIST):
    case FAILURE(ACTION_TYPES.FETCH_CLIENTEMAILLIST):
    case FAILURE(ACTION_TYPES.CREATE_CLIENTEMAILLIST):
    case FAILURE(ACTION_TYPES.UPDATE_CLIENTEMAILLIST):
    case FAILURE(ACTION_TYPES.DELETE_CLIENTEMAILLIST):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_CLIENTEMAILLIST_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_CLIENTEMAILLIST):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_CLIENTEMAILLIST):
    case SUCCESS(ACTION_TYPES.UPDATE_CLIENTEMAILLIST):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_CLIENTEMAILLIST):
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

const apiUrl = 'api/client-email-lists';

// Actions

export const getEntities: ICrudGetAllAction<IClientEmailList> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_CLIENTEMAILLIST_LIST,
    payload: axios.get<IClientEmailList>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IClientEmailList> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_CLIENTEMAILLIST,
    payload: axios.get<IClientEmailList>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IClientEmailList> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_CLIENTEMAILLIST,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IClientEmailList> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_CLIENTEMAILLIST,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IClientEmailList> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_CLIENTEMAILLIST,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
