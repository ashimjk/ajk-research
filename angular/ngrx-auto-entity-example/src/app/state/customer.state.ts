import {buildState, IEntityState} from '@briebug/ngrx-auto-entity';
import {Customer} from 'src/app/models';
import {createFeatureSelector} from '@ngrx/store';

export const {initialState: customerInitialState, facade: CustomerFacadeBase} = buildState(Customer);


export function customerReducer(state = customerInitialState): IEntityState<Customer> {
  return state;
}
