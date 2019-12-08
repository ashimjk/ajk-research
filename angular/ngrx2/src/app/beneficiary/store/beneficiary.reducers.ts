import {ActionReducerMap, createReducer, on} from '@ngrx/store';
import {FeatureState, initialBeneficiaryState} from './beneficiary.state';
import {createBeneficiaryFail, createBeneficiarySuccess, loadBeneficiariesFail, loadBeneficiariesSuccess} from './beneficiary.actions';


export const beneficiaryReducer = createReducer(initialBeneficiaryState,
  on(createBeneficiarySuccess, (state, action) => ({
    ...state, beneficiaries: [...state.beneficiaries, action.beneficiary]
  })),

  on(createBeneficiaryFail, (state, action) => ({...state, error: action.error})),

  on(loadBeneficiariesSuccess, (state, action) => ({
    ...state, beneficiaries: [...state.beneficiaries, ...action.beneficiaries]
  })),

  on(loadBeneficiariesFail, (state, action) => ({...state, error: action.error})),
);


export const reducers: ActionReducerMap<FeatureState> = {
  beneficiaryState: beneficiaryReducer
};
