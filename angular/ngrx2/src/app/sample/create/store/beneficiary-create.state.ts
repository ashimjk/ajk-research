import {createFormGroupState, updateGroup, validate} from 'ngrx-forms';

import {required} from 'ngrx-forms/validation';
import {IdTypeRequest} from '../../idTypeRequest';


export class BeneficiaryCreateForm {
  reference: string;
  fullName: string;
  beneficiaryType: string;
  idTypes?: IdTypeRequest[];
  // idTypes?: FormGroupState<IdTypeRequest>[];
}

export const BENEFICIARY_CREATE_FORM_ID = 'beneficiaryCreateForm';

export const BENEFICIARY_CREATE_FORM_INITIAL_STATE = createFormGroupState<BeneficiaryCreateForm>(BENEFICIARY_CREATE_FORM_ID,
  {
    reference: '',
    fullName: '',
    beneficiaryType: '',
    idTypes: [],
  }
);

export const ID_TYPE_INITIAL_STATE: IdTypeRequest = {
  name: '',
  number: '',
  document: '',
  expiryDate: ''
};


export const validateBeneficiaryCreateForm = updateGroup<BeneficiaryCreateForm>({
  reference: validate<string>(required),
  fullName: validate(required),
  beneficiaryType: validate(required),

  // idTypes: updateArray(updateGroup<IdTypeRequest>({
  //   name: validate(required),
  //   number: validate(required),
  //   document: validate(required),
  //   expiryDate: validate(required)
  // }))
});
