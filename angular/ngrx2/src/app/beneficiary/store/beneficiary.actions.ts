import {createAction, props} from '@ngrx/store';
import {Beneficiary} from './beneficiary.state';

export enum BeneficiaryActions {
  CREATE_BENEFICIARY = '[BENEFICIARY] CREATE BENEFICIARY',
  CREATE_BENEFICIARY_SUCCESS = '[BENEFICIARY] CREATE BENEFICIARY SUCCESS',
  CREATE_BENEFICIARY_FAIL = '[BENEFICIARY] CREATE BENEFICIARY FAILED',

  LOAD_BENEFICIARIES = '[BENEFICIARY] LOAD BENEFICIARY',
  LOAD_BENEFICIARIES_SUCCESS = '[BENEFICIARY] LOAD BENEFICIARY SUCCESS',
  LOAD_BENEFICIARIES_FAIL = '[BENEFICIARY] LOAD BENEFICIARY FAILED',
}

export const createBeneficiary = createAction(BeneficiaryActions.CREATE_BENEFICIARY, props<{ beneficiary: Beneficiary }>());
export const createBeneficiarySuccess = createAction(BeneficiaryActions.CREATE_BENEFICIARY_SUCCESS, props<{ beneficiary: Beneficiary }>());
export const createBeneficiaryFail = createAction(BeneficiaryActions.CREATE_BENEFICIARY_FAIL, props<{ error: string }>());

export const loadBeneficiaries = createAction(BeneficiaryActions.LOAD_BENEFICIARIES);
export const loadBeneficiariesSuccess = createAction(
  BeneficiaryActions.LOAD_BENEFICIARIES_SUCCESS,
  props<{ beneficiaries: Beneficiary[] }>()
);
export const loadBeneficiariesFail = createAction(BeneficiaryActions.LOAD_BENEFICIARIES_FAIL, props<{ error: string }>());

