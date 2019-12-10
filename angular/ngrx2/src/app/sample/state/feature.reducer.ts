import {ActionReducerMap} from '@ngrx/store';

import {FeatureState} from './feature.state';
import {beneficiaryCreateFormStateReducer} from '../create/store/beneficiary.reducers';

export const featureReducer: ActionReducerMap<FeatureState> = {
  beneficiaryCreateForm: beneficiaryCreateFormStateReducer
};

