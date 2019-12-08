import {createReducer, on} from '@ngrx/store';
import {initialUserState} from './user.state';
import {addUser, addUsers} from './user.actions';

export const userReducer = createReducer(initialUserState,
  on(addUsers, (state, action) => {
    return {
      ...state,
      users: [...action.users]
    };
  }),

  on(addUser, ((state, action) => {
    return {
      ...state,
      users: [...state.users, action.user]
    };
  }))
);
