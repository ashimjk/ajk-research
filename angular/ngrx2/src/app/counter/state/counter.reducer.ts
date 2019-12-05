import {createFeatureSelector, createReducer, createSelector, on} from '@ngrx/store';
import {decrement, increment, reset} from './counter.action';

export const FEATURE_APP_STATE = 'appState';

export class AppState {
  value: number;
}

export const featureSelector = createFeatureSelector<AppState>(FEATURE_APP_STATE);

export const counterSelector = createSelector(featureSelector, s1 => s1.value);

export const initialAppState: AppState = {
  value: 0
};

const _appReducer = createReducer(initialAppState,
  on(increment, state => {
    state.value++;
    return {...state};
  }),
  on(decrement, state => {
    state.value--;
    return state;
  }),
  on(reset, state => {
    state.value = 0;
    return state;
  }),
);

export function appReducer(state: AppState = initialAppState, action): AppState {
  return _appReducer(state, action);
}
