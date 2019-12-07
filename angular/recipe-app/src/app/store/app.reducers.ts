import {authReducer} from '../auth/store/auth.reducers';
import {ActionReducerMap} from '@ngrx/store';
import {shoppingListReducer} from '../shopping-list/store/shopping-list.reducers';
import {AppState} from './app.state';

export const reducers: ActionReducerMap<AppState> = {
  shoppingList: shoppingListReducer,
  auth: authReducer
};
