import {User} from '../user';
import {createFeatureSelector, createSelector} from '@ngrx/store';

export interface UserState {
  maskUserName: boolean;
  currentUser: User;
}

export const initialState: UserState = {
  maskUserName: false,
  currentUser: null
};

const featureSelector = createFeatureSelector<UserState>('users');
export const maskUserNameSelector = createSelector(featureSelector, users => users.maskUserName);

export function reducer(state = initialState, action): UserState {
  switch (action.type) {
    case 'MASK_USER_NAME':
      console.log('existing state: ' + JSON.stringify(state));
      console.log('action: ' + JSON.stringify(action));

      return {...state, maskUserName: action.payload};

    default:
      return state;
  }
}
