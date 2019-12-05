import {createAction, props} from '@ngrx/store';
import {User} from '../model/user';

export enum UserActionType {
  LOGIN = '[USER] Login',
  LOGIN_SUCCESS = '[USER] Login Success',
  LOGIN_FAIL = '[USER] Login Fail'
}

export class UserActions {
  public static LOGIN = createAction(UserActionType.LOGIN, props<User>());
  static LOGIN_SUCCESS = createAction(UserActionType.LOGIN_SUCCESS, props<User>());
  static LOGIN_FAIL = createAction(UserActionType.LOGIN_FAIL, props<User>());
}

