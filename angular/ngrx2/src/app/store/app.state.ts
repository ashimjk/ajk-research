import {UserState} from '../users/store/user.state';
import {FormValue} from '../forms/reducer';
import {FormGroupState} from 'ngrx-forms';

export interface AppState {
  userState: UserState;
  myForm: FormGroupState<FormValue>;
}
