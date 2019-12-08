import {createReducer, on} from '@ngrx/store';
import {initialUserState} from './user.state';
import {addUserFail, addUserSuccess, loadUsersFail, loadUsersSuccess} from './user.actions';

export const userReducer = createReducer(initialUserState,

  on(addUserSuccess, ((state, action) => {
    return {
      ...state,
      users: [...state.users, action.user],
      error: ''
    };
  })),

  on(addUserFail, ((state, action) => {
    return {
      ...state,
      error: action.error
    };
  })),

  on(loadUsersSuccess, (state, action) => {
    return {
      ...state,
      users: [...action.users],
      error: ''
    };
  }),

  on(loadUsersFail, (state, action) => {
    return {
      ...state,
      error: action.error
    };
  }),
);
