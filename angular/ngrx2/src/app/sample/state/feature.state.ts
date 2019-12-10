import {FormGroupState} from 'ngrx-forms';
import {BeneficiaryCreateForm} from '../create/store/beneficiary-create.state';


export interface IFeatureState {
  beneficiaryCreateForm: FormGroupState<BeneficiaryCreateForm>;
}

export type FeatureState = IFeatureState;
