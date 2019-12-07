import {AuthActionsType, LOGOUT, SET_TOKEN, SIGNIN, SIGNUP} from './auth.actions';
import {AuthState, initialAuthState} from './auth.state';

export function authReducer(state: AuthState = initialAuthState, action: AuthActionsType) {

  switch (action.type) {

    case SIGNUP:
    case SIGNIN:
      return {
        ...state,
        authenticated: true
      };

    case LOGOUT:
      return {
        ...state,
        token: null,
        authenticated: false
      };

    case SET_TOKEN:
      return {
        ...state,
        token: action.payload,
        authenticated: true
      };
  }
  return state;
}
