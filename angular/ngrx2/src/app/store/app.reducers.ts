import {ActionReducerMap} from '@ngrx/store';
import {AppState} from './app.state';
import {userReducer} from '../users/store/user.reducers';
import {myFormReducer} from '../forms/reducer';

export const reducers: ActionReducerMap<AppState> = {
  userState: userReducer,
  myForm: myFormReducer
};
