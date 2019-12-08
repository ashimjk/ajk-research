import {createAction, props} from '@ngrx/store';
import {User} from './user.state';

export enum UserActions {
  ADD_USER = '[USER] ADD USER',
  ADD_USERS = '[USER] ADD USERS'
}

export const addUsers = createAction(UserActions.ADD_USERS, props<{ users: User[] }>());
export const addUser = createAction(UserActions.ADD_USER, props<{ user: User }>());
