import {Action} from '@ngrx/store';
import {createFormGroupState, formGroupReducer, FormGroupState} from 'ngrx-forms';

export interface FormValue {
  firstName: string;
  lastName: string;
  email: string;
  sex: string;
  favoriteColor: string;
  employed: boolean;
  notes: string;
  account: {
    number: number;
  };
}

const FORM_ID = 'simpleForm';

export const INITIAL_STATE = createFormGroupState<FormValue>(FORM_ID, {
  firstName: '',
  lastName: '',
  email: '',
  sex: '',
  favoriteColor: '',
  employed: false,
  notes: '',
  account: {
    number: -1
  }
});

export class SetSubmittedValueAction implements Action {
  static readonly TYPE = 'simpleForm/SET_SUBMITTED_VALUE';
  readonly type = SetSubmittedValueAction.TYPE;

  constructor(public submittedValue: FormGroupState<FormValue>) {
  }
}

export type FormActionsType = SetSubmittedValueAction;

export function myFormReducer(state = INITIAL_STATE, action: FormActionsType): FormGroupState<FormValue> {
  const myForm = formGroupReducer(state, action);
  if (myForm !== state) {
    state = {...state, ...myForm};
  }

  switch (action.type) {
    case SetSubmittedValueAction.TYPE:
      return action.submittedValue;

    default: {
      return state;
    }
  }
}

// import { createReducer } from '@ngrx/store';
// import { onNgrxForms } from 'ngrx-forms';
//
// export const appReducer = createReducer(
//   initialState,
//   onNgrxForms(),
//   // your other reducers...
// );
