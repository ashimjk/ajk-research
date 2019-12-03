import {createReducer, on} from '@ngrx/store';
import {decrement, increment, reset, setValue} from './counter.actions';

export const initialState = 0;

const _counterReducer = createReducer(initialState,
  on(increment, state => state + 1),
  on(decrement, state => state - 1),
  on(reset, state => 0),
  on(setValue, (state, action) => state = action.value)
);

export function counterReducer(state, action) {
  return _counterReducer(state, action);
}
