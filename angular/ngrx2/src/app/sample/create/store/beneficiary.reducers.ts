import {Action} from "@ngrx/store";
import {
  addArrayControl,
  createFormArrayState, createFormControlState,
  createFormGroupState,
  formGroupReducer,
  FormGroupState,
  FormState,
  removeArrayControl,
  setValue,
  updateGroup
} from 'ngrx-forms';
import {
  BENEFICIARY_CREATE_FORM_INITIAL_STATE,
  BeneficiaryCreateForm,
  ID_TYPE_INITIAL_STATE,
  validateBeneficiaryCreateForm
} from "./beneficiary-create.state";
import {AddFormControl, AddIDTypes, RemoveFormControl} from "./beneficiary.actions";
import {IdTypeRequest} from "@corpay/beneficiary-client";
import {FormArrayState} from "ngrx-forms/src/state";


export function beneficiaryCreateFormStateReducer(state = BENEFICIARY_CREATE_FORM_INITIAL_STATE, action: Action) {
  const createForm = validateBeneficiaryCreateForm(formGroupReducer(state, action));
  if (createForm !== state) {
    state = {...state, ...createForm};
  }

  function check(idTypes: FormState<BeneficiaryCreateForm["idTypes"]>) {
    return addArrayControl(idTypes);
  }

  const array = createFormArrayState<IdTypeRequest>('idTypes', [ID_TYPE_INITIAL_STATE]);

  // function addArrayControl<TValue>(value: TValue, index?: number): (state: FormArrayState<TValue>) => FormArrayState<TValue>{
  //   const group = createFormControlState<IdTypeRequest>('beneficiaryCreateForm.idTypes', ID_TYPE_INITIAL_STATE);
  //  return createFormArrayState<IdTypeRequest>('beneficiaryCreateForm.idTypes', [group]);
  // }

  switch (action.type) {

    case AddFormControl.actionType: {

      console.log(state.controls.idTypes);

      // const group = createFormGroupState<IdTypeRequest>('beneficiaryCreateForm.idTypes', ID_TYPE_INITIAL_STATE);
      //
      // const arrayState = createFormArrayState<FormGroupState<IdTypeRequest>>('beneficiaryCreateForm.idTypes', [this.group]);
      //
      // addArrayControl(group)(state.controls.idTypes);
      //

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

    case AddIDTypes.actionType:
      // state = updateGroup<BeneficiaryCreateForm>(state, {
      // idTypes: addArrayControl((action as AddIDTypes).idTypeGroupControl)
      // });

      // let idTypeGroupControl = (action as AddIDTypes).idTypeGroupControl;
      // state = updateGroup<BeneficiaryCreateForm>(state, {
      //   // idTypes: setValue(state.controls.idTypes, idTypeGroupControl)
      //   idTypes: array()
      // });


      return setValue({...state.value})(state);

    default:
      return state;
  }
}
