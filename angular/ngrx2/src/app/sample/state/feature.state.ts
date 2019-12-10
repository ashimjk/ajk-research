import {IEntityState} from '@briebug/ngrx-auto-entity';
import {BeneficiaryEntity} from '../model/entity.model';
import {FormGroupState} from 'ngrx-forms';
import {BeneficiaryCreateForm} from "../ben/create/store/beneficiary-create.state";


export interface IFeatureState {
  beneficiaryEntity: IEntityState<BeneficiaryEntity>;
  beneficiaryCreateForm: FormGroupState<BeneficiaryCreateForm>
  // beneficiaryCreateForm: FormGroupState<BeneficiaryCreateFormValue>;
  // addVerificationIdCreateForm: FormGroupState<AddVerificationIdFormValue>;
  // addAddressCreateForm: FormGroupState<AddAddressFormValue>;
  // addKeyManagementCreateForm: FormGroupState<AddKeyManagementFormValue>;
  // addShareHolderCreateForm: FormGroupState<AddShareHolderFormValue>;
  // addAuthorizedSignatoriesCreateForm: FormGroupState<AddAuthorizedSignatoriesFormValue>;
  // addDelegatedPersonsCreateForm: FormGroupState<AddDelegatedPersonsFormValue>;
  // addContactPersonsCreateForm: FormGroupState<AddContactPersonsFormValue>;
  // addCorrespondentBanksCreateForm: FormGroupState<AddCorrespondentBanksFormValue>;
}

export type FeatureState = IFeatureState;
