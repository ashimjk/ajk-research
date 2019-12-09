import {createFeatureSelector, createSelector} from '@ngrx/store';

export const beneficiaryFeatureKey = 'beneficiaryFeature';

export interface FeatureState {
  [beneficiaryFeatureKey]: BeneficiaryState;
}

export interface BeneficiaryState {
  beneficiary: BeneficiaryComponentState;
}

export interface BeneficiaryComponentState {
  beneficiaries: Beneficiary[];
  currentBeneficiary: Beneficiary;
  error: string;
}

export interface Beneficiary {
  reference: string;
  amount: number;
}

export const initialBeneficiaryState: BeneficiaryComponentState = {
  beneficiaries: [],
  currentBeneficiary: null,
  error: ''
};

export const selectBeneficiaryState = createFeatureSelector<BeneficiaryState>(beneficiaryFeatureKey);

export const selectBeneficiaryComponentState = createSelector(
  selectBeneficiaryState,
  (state: BeneficiaryState) => state.beneficiary
);

export const selectAllBeneficiary = createSelector(
  selectBeneficiaryComponentState,
  (state: BeneficiaryComponentState) => state.beneficiaries
);

export const selectError = createSelector(selectBeneficiaryComponentState, state => state.error);

