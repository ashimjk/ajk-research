export interface FeatureState {
  beneficiaryState: BeneficiaryState;
}

export interface Beneficiary {
  reference: string;
  amount: number;
}

export interface BeneficiaryState {
  beneficiaries: Beneficiary[];
  currentBeneficiary: Beneficiary;
  error: string;
}

export const initialBeneficiaryState: BeneficiaryState = {
  beneficiaries: [],
  currentBeneficiary: null,
  error: ''
};

