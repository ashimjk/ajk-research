import {IEntityState} from '@briebug/ngrx-auto-entity';
import {ActionReducerMap} from '@ngrx/store';

import {Customer} from '../models';
import {customerReducer} from './customer.state';

export interface IAppState {
  customer: IEntityState<Customer>;
}

export type AppState = IAppState;

export const appReducer: ActionReducerMap<IAppState> = {
  customer: customerReducer
};
