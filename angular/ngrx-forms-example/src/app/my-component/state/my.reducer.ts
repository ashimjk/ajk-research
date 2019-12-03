import {Action} from '@ngrx/store';
import {createFormGroupState, formStateReducer} from 'ngrx-forms';

export interface MyFormValue {
  stringValue: string;
}

export const initialState = createFormGroupState<MyFormValue>('FORM', {
  stringValue: ''
});

export function formReducer(state = initialState, action: Action) {
  return formStateReducer(state, action);
}
