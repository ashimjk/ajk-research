import {createAction, props} from '@ngrx/store';
import {User} from './user.state';

export enum UserActions {
  ADD_USER = '[USER] ADD USER',
  ADD_USER_SUCCESS = '[USER] ADD USER SUCCESS',
  ADD_USER_FAIL = '[USER] ADD USER FAILED',

  LOAD_USERS = '[USER] GET USERS',
  LOAD_USERS_SUCCESS = '[USER] GET USERS SUCCESS',
  LOAD_USERS_FAIL = '[USER] GET USERS FAILED'
}

export const addUser = createAction(UserActions.ADD_USER, props<{ user: User }>());
export const addUserSuccess = createAction(UserActions.ADD_USER_SUCCESS, props<{ user: User }>());
export const addUserFail = createAction(UserActions.ADD_USER_FAIL, props<{ error: string }>());

export const loadUsers = createAction(UserActions.LOAD_USERS);
export const loadUsersSuccess = createAction(UserActions.LOAD_USERS_SUCCESS, props<{ users: User[] }>());
export const loadUsersFail = createAction(UserActions.LOAD_USERS_FAIL, props<{ error: string }>());
