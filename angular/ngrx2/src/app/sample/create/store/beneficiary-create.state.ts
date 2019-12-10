import {createFormGroupState, updateArray, updateGroup, validate} from "ngrx-forms";

import {
  AccountRequest,
  AddressRequest,
  AuthorizedSignatureRequest,
  BeneficiaryRequest,
  ContactPersonRequest,
  ContractDetailRequest,
  CorporateBeneficiaryRequest,
  CorrespondentBankRequest,
  DelegatedPersonRequest,
  IdTypeRequest,
  KeyManagementRequest,
  ServiceRequest,
  ShareHolderRequest,
  TierRequest,
  TypeOfDealingRequest
} from "@corpay/beneficiary-client";
import {required} from "ngrx-forms/validation";


export class BeneficiaryCreateForm implements BeneficiaryRequest {
  reference: string;
  fullName: string;
  beneficiaryType: BeneficiaryRequest.BeneficiaryTypeEnum;
  idTypes?: IdTypeRequest[];
  corporateBeneficiary?: CorporateBeneficiaryRequest;
  addresses?: Array<AddressRequest>;
  authorizedSignatories?: Array<AuthorizedSignatureRequest>;
  correspondentBanks?: Array<CorrespondentBankRequest>;
  typeOfDealings?: Array<TypeOfDealingRequest>;
  startingDate?: string;
  reviewDate?: string;
  endingDate?: string;
  contractDetail?: ContractDetailRequest;
}

export const BENEFICIARY_CREATE_FORM_ID = 'beneficiaryCreateForm';

export const BENEFICIARY_CREATE_FORM_INITIAL_STATE = createFormGroupState<BeneficiaryCreateForm>(BENEFICIARY_CREATE_FORM_ID,
  {
    reference: '',
    fullName: '',
    beneficiaryType: null,
    idTypes: [],
    corporateBeneficiary: {
      corporateId: '',
      contactPersons: [],
      keyManagements: [],
      shareHolders: []
    },
    addresses: [],
    authorizedSignatories: [],
    correspondentBanks: [],
    typeOfDealings: [],
    startingDate: '',
    reviewDate: '',
    endingDate: '',
    contractDetail: {
      number: -1,
      from: '',
      to: '',
      detail: '',
      amount: '',
      balance: '',
      feesAndCharges: '',
      termsOfPayment: null
    }
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

  idTypes: updateArray(updateGroup<IdTypeRequest>({
    name: validate(required),
    number: validate(required),
    document: validate(required),
    expiryDate: validate(required)
  })),

  corporateBeneficiary: updateGroup<CorporateBeneficiaryRequest>({
    corporateId: validate(required),

    contactPersons: updateArray(updateGroup<ContactPersonRequest>({
      title: validate(required),
      fullName: validate(required),
      email: validate(required),
      phoneNo: validate(required),
      address: updateGroup<AddressRequest>({
        addressName: validate(required),
        addressLine1: validate(required),
        addressLine2: validate(required),
        city: validate(required),
        country: validate(required),
        poBox: validate(required)
      }),
      nationalNumber: validate(required),
      services: validate(required)
    })),

    keyManagements: updateArray(updateGroup<KeyManagementRequest>({
      fullName: validate(required),
      title: validate(required),
      nationalNumber: validate(required)
    })),

    shareHolders: updateArray(updateGroup<ShareHolderRequest>({
      fullName: validate(required),
      nationalNumber: validate(required)
    })),

  }),

  addresses: updateArray(updateGroup<AddressRequest>({
    addressName: validate(required),
    addressLine1: validate(required),
    addressLine2: validate(required),
    city: validate(required),
    country: validate(required),
    poBox: validate(required)
  })),

  authorizedSignatories: updateArray(updateGroup<AuthorizedSignatureRequest>({
    title: validate(required),
    fullName: validate(required),
    nationalNumber: validate(required),
    signature: validate(required),
    document: validate(required),

    delegatedPerson: updateGroup<DelegatedPersonRequest>({
      title: validate(required),
      fullName: validate(required),
      nationalNumber: validate(required),
      signature: validate(required),
      document: validate(required),
      services: validate(required)
    }),

    services: updateArray(updateGroup<ServiceRequest>({
      name: validate(required),
      tier: updateGroup<TierRequest>({
        from: validate(required),
        to: validate(required)
      })
    }))
  })),

  correspondentBanks: updateArray(updateGroup<CorrespondentBankRequest>({
    name: validate(required),
    bicCode: validate(required),
    address: updateGroup<AddressRequest>({
      addressName: validate(required),
      addressLine1: validate(required),
      addressLine2: validate(required),
      city: validate(required),
      country: validate(required),
      poBox: validate(required)
    }),
    services: validate(required),
    accounts: updateArray(updateGroup<AccountRequest>({
      iban: validate(required),
      currency: validate(required),
      accountNumber: validate(required),
      accountAlias: validate(required)
    }))
  })),

  typeOfDealings: updateArray(updateGroup<TypeOfDealingRequest>({
    dealType: validate(required),
    services: validate(required)
  })),

  startingDate: validate(required),
  endingDate: validate(required),

  contractDetail: updateGroup<ContractDetailRequest>({
    number: validate(required),
    from: validate(required),
    to: validate(required),
    detail: validate(required),
    amount: validate(required),
    balance: validate(required),
    feesAndCharges: validate(required),
    termsOfPayment: validate(required)
  })
});
