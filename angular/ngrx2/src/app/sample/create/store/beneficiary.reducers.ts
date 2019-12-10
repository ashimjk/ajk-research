import {Action} from '@ngrx/store';
import {addArrayControl, formGroupReducer, removeArrayControl, setValue, updateGroup} from 'ngrx-forms';
import {BENEFICIARY_CREATE_FORM_INITIAL_STATE, BeneficiaryCreateForm, validateBeneficiaryCreateForm} from './beneficiary-create.state';
import {AddFormControl, RemoveFormControl} from './beneficiary.actions';
import {IdTypeRequest} from '../../idTypeRequest';

export function beneficiaryCreateFormStateReducer(state = BENEFICIARY_CREATE_FORM_INITIAL_STATE, action: Action) {
  const createForm = validateBeneficiaryCreateForm(formGroupReducer(state, action));
  if (createForm !== state) {
    state = {...state, ...createForm};
  }

  switch (action.type) {

    case AddFormControl.actionType: {

      state = updateGroup<BeneficiaryCreateForm>(state, {
        idTypes: addArrayControl((action as AddFormControl<IdTypeRequest>).payload)
      });

      return setValue({...state.value})(state);
    }
    case RemoveFormControl.actionType:

      state = updateGroup<BeneficiaryCreateForm>(state, {
        idTypes: removeArrayControl((action as RemoveFormControl<IdTypeRequest>).index)
      });

      return setValue({...state.value})(state);

    default:
      return state;
  }
}
