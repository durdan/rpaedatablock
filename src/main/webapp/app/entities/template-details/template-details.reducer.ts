import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ITemplateDetails, defaultValue } from 'app/shared/model/template-details.model';

export const ACTION_TYPES = {
  FETCH_TEMPLATEDETAILS_LIST: 'templateDetails/FETCH_TEMPLATEDETAILS_LIST',
  FETCH_TEMPLATEDETAILS: 'templateDetails/FETCH_TEMPLATEDETAILS',
  CREATE_TEMPLATEDETAILS: 'templateDetails/CREATE_TEMPLATEDETAILS',
  UPDATE_TEMPLATEDETAILS: 'templateDetails/UPDATE_TEMPLATEDETAILS',
  DELETE_TEMPLATEDETAILS: 'templateDetails/DELETE_TEMPLATEDETAILS',
  RESET: 'templateDetails/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ITemplateDetails>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type TemplateDetailsState = Readonly<typeof initialState>;

// Reducer

export default (state: TemplateDetailsState = initialState, action): TemplateDetailsState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_TEMPLATEDETAILS_LIST):
    case REQUEST(ACTION_TYPES.FETCH_TEMPLATEDETAILS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_TEMPLATEDETAILS):
    case REQUEST(ACTION_TYPES.UPDATE_TEMPLATEDETAILS):
    case REQUEST(ACTION_TYPES.DELETE_TEMPLATEDETAILS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_TEMPLATEDETAILS_LIST):
    case FAILURE(ACTION_TYPES.FETCH_TEMPLATEDETAILS):
    case FAILURE(ACTION_TYPES.CREATE_TEMPLATEDETAILS):
    case FAILURE(ACTION_TYPES.UPDATE_TEMPLATEDETAILS):
    case FAILURE(ACTION_TYPES.DELETE_TEMPLATEDETAILS):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_TEMPLATEDETAILS_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_TEMPLATEDETAILS):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_TEMPLATEDETAILS):
    case SUCCESS(ACTION_TYPES.UPDATE_TEMPLATEDETAILS):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_TEMPLATEDETAILS):
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

const apiUrl = 'api/template-details';

// Actions

export const getEntities: ICrudGetAllAction<ITemplateDetails> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_TEMPLATEDETAILS_LIST,
    payload: axios.get<ITemplateDetails>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<ITemplateDetails> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_TEMPLATEDETAILS,
    payload: axios.get<ITemplateDetails>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ITemplateDetails> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_TEMPLATEDETAILS,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ITemplateDetails> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_TEMPLATEDETAILS,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<ITemplateDetails> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_TEMPLATEDETAILS,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
