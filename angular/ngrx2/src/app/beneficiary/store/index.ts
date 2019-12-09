import {BeneficiaryState} from './beneficiary.state';
import {ActionReducerMap} from '@ngrx/store';
import {beneficiaryComponentReducer} from './beneficiary.reducers';

export const reducers: ActionReducerMap<BeneficiaryState> = {
  beneficiary: beneficiaryComponentReducer
};
