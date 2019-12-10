import {Action} from '@ngrx/store';
import {BENEFICIARY_CREATE_FORM_ID} from './beneficiary-create.state';
import {FormArrayState} from 'ngrx-forms';
import {IdTypeRequest} from '../../idTypeRequest';


export class AddFormControl<T> implements Action {
  public static actionType = `[${BENEFICIARY_CREATE_FORM_ID}] Add form control`;
  type: string = AddFormControl.actionType;

  constructor(public payload: T) {
  }
}

export class RemoveFormControl<T> implements Action {
  public static actionType = `[${BENEFICIARY_CREATE_FORM_ID}] Remove form control`;
  type: string = RemoveFormControl.actionType;

  constructor(public index: number) {
  }
}

export class AddIDTypes implements Action {
  public static actionType = '[CREATE BENEFICIARY] Add IDTypes';
  readonly type = AddIDTypes.actionType;

  constructor(public idTypeGroupControl: FormArrayState<IdTypeRequest>) {
  }
}
