import {ActionReducerMap} from '@ngrx/store';

import {FeatureState} from './feature.state';
import {beneficiaryEntityReducer} from '../ben/state/beneficiary.state';
import {beneficiaryCreateFormStateReducer} from "../ben/create/store/beneficiary.reducers";

export const featureReducer: ActionReducerMap<FeatureState> = {
  beneficiaryEntity: beneficiaryEntityReducer,
  beneficiaryCreateForm: beneficiaryCreateFormStateReducer,
  // beneficiaryCreateForm: beneficiaryCreateFormStateReducer,
  // addVerificationIdCreateForm: addVerificationIdCreateFormStateReducer,
  // addAddressCreateForm: addAddressCreateFormStateReducer,
  // addKeyManagementCreateForm: addKeymanagementCreateFormStateReducer,
  // addShareHolderCreateForm: addShareHolderCreateFormStateReducer,
  // addAuthorizedSignatoriesCreateForm: addAuthorizedSignatoriesCreateFormStateReducer,
  // addDelegatedPersonsCreateForm: addDelegatedPersonsCreateFormStateReducer,
  // addContactPersonsCreateForm: addContactPersonsCreateFormStateReducer,
  // addCorrespondentBanksCreateForm: addCorrespondentBanksCreateFormStateReducer
};

