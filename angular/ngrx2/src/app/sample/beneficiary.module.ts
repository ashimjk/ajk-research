import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {CommonModule} from '@angular/common';
import {CorpayContainerModule} from '@corpay/layouts';
import {CorpayControlsModule} from '@corpay/controls';
import {CorpayGridsModule} from '@corpay/grids';
import {EffectsModule} from '@ngrx/effects';
import {StoreModule} from '@ngrx/store';
import {NgrxAutoEntityModule} from '@briebug/ngrx-auto-entity';
import {NgrxFormsModule} from 'ngrx-forms';
import {BeneficiaryListComponent} from './ben/ben-list/beneficiary-list.component';
import {BeneficiaryCreateComponent} from './ben/ben-create/beneficiary-create.component';
import {BeneficiaryEntityEffects} from './ben/effects/beneficiary-entity-effects.service';
import {featureReducer} from './state/feature.reducer';
import {BENEFICIARY_FEATURE_NAME} from './state/contants';
import {BeneficiaryEntityService} from './ben/facades/beneficiary-entity.service';
import {BeneficiaryEntity} from './model/entity.model';
import {BeneficiaryComponent} from './beneficiary.component';
import {BeneficiaryInfoComponent} from './ben/ben-create/basic-info/beneficiary-info/beneficiary-info.component';
import {ControlsModule} from './controls/controls.module';
import {VerificationIdCardActionBodyComponent} from './ben/ben-create/basic-info/verification-ids/verification-id-card-action/verification-id-card-action-body.component';
import {VerificationIdsComponent} from './ben/ben-create/basic-info/verification-ids/verification-ids.component';
import {VerificationIdAddFormComponent} from './ben/ben-create/basic-info/verification-ids/verification-id-add-form/verification-id-add-form.component';
import {FormsModule} from '@angular/forms';
import {AddressesComponent} from './ben/ben-create/basic-info/addresses/addresses.component';
import {AddressCardActionBodyComponent} from './ben/ben-create/basic-info/addresses/address-card-action/address-card-action-body.component';
import {KeyManagementComponent} from './ben/ben-create/basic-info/key-management/key-management.component';
import {KeyManagementCardActionBodyComponent} from './ben/ben-create/basic-info/key-management/key-management-card-action/key-management-card-action-body.component';
import {ProfileUploadComponent} from './controls/profile-upload/profile-upload.component';
import {ShareHolderComponent} from './ben/ben-create/basic-info/share-holder/share-holder.component';
import {ShareHolderCardActionBodyComponent} from './ben/ben-create/basic-info/share-holder/share-holder-card-action/share-holder-card-action-body.component';
import {AuthorizedSignatoriesComponent} from './ben/ben-create/basic-info/authorized-signatories/authorized-signatories.component';
import {AuthorizedSignatoriesCardActionBodyComponent} from './ben/ben-create/basic-info/authorized-signatories/authorized-signatories-card-action/authorized-signatories-card-action-body.component';
import {DelegatedPersonsComponent} from './ben/ben-create/basic-info/delegated-persons/delegated-persons.component';
import {DelegatedPersonsCardActionBodyComponent} from './ben/ben-create/basic-info/delegated-persons/delegated-persons-card-action/delegated-persons-card-action-body.component';
import {ContactPersonsComponent} from "./ben/ben-create/basic-info/contact-persons/contact-persons.component";
import {ContactPersonsCardActionBodyComponent} from "./ben/ben-create/basic-info/contact-persons/contact-persons-card-action/contact-persons-card-action-body.component";
import {CorrespondentBanksComponent} from "./ben/ben-create/basic-info/correspondent-banks/correspondent-banks.component";
import {CorrespondentBanksCardActionBodyComponent} from "./ben/ben-create/basic-info/correspondent-banks/correspondent-banks-card-action/correspondent-banks-card-action-body.component";
import {AccountsComponent} from './ben/ben-create/basic-info/correspondent-banks/accounts/accounts.component';
import {TypeOfDealingComponent} from "./ben/ben-create/basic-info/type-of-dealing/type-of-dealing.component";
import {RelationshipDatesComponent} from "./ben/ben-create/basic-info/relationship-dates/relationship-dates.component";
import {InputTextComponent} from "./controls/input-text/input-text.component";
import {NgbDateCustomParserFormatter} from "./ben/formatter/custom-date-parser-formatter";
import {NgbDateParserFormatter} from "@ng-bootstrap/ng-bootstrap";
import {CreateBeneficiaryComponent} from "./ben/create/create-beneficiary.component";
import {BfcBeneficiaryInfoComponent} from "./ben/create/basic-info/beneficiary-info/bfc-beneficiary-info.component";
import {BfcVerificationIdsComponent} from "./ben/create/basic-info/verification-ids/bfc-verification-ids.component";
// import {LetterOfCreditApiService} from '@contract/tf-importlc-angular-contracts';

const OUTLET = 'tf';

const routes: Routes = [
  {
    path: '',
    component: BeneficiaryComponent,
    children: [
      {
        path: '',
        children: [
          {
            path: '',
            component: BeneficiaryListComponent
          },
          {
            path: 'create',
            component: CreateBeneficiaryComponent
          }
        ]
      }
    ]
  }
];

@NgModule({
  declarations: [
    BeneficiaryComponent,
    BeneficiaryListComponent,
    BeneficiaryCreateComponent,
    BeneficiaryInfoComponent,
    VerificationIdCardActionBodyComponent,
    VerificationIdsComponent,
    VerificationIdAddFormComponent,
    AddressesComponent,
    AddressCardActionBodyComponent,
    KeyManagementComponent,
    KeyManagementCardActionBodyComponent,
    ProfileUploadComponent,
    ShareHolderComponent,
    ShareHolderCardActionBodyComponent,
    AuthorizedSignatoriesComponent,
    AuthorizedSignatoriesCardActionBodyComponent,
    ContactPersonsComponent,
    ContactPersonsCardActionBodyComponent,
    DelegatedPersonsComponent,
    DelegatedPersonsCardActionBodyComponent,
    CorrespondentBanksComponent,
    CorrespondentBanksCardActionBodyComponent,
    AccountsComponent,
    DelegatedPersonsCardActionBodyComponent,
    TypeOfDealingComponent,
    RelationshipDatesComponent,
    InputTextComponent,

    CreateBeneficiaryComponent,
    BfcBeneficiaryInfoComponent,
    BfcVerificationIdsComponent,
  ],
  imports: [
    CommonModule,
    CorpayContainerModule,
    CorpayControlsModule,
    CorpayGridsModule,
    ControlsModule,
    FormsModule,
    NgrxFormsModule,
    EffectsModule.forFeature([BeneficiaryEntityEffects]),
    StoreModule.forFeature(BENEFICIARY_FEATURE_NAME, featureReducer),
    NgrxAutoEntityModule.forFeature(),
    CorpayGridsModule,
    RouterModule.forChild(routes)
  ],
  exports: [BeneficiaryComponent],
  providers: [
    {
      provide: BeneficiaryEntity, useClass: BeneficiaryEntityService
    },
    {
      provide: NgbDateParserFormatter, useClass: NgbDateCustomParserFormatter
    }
    ,
    // LetterOfCreditApiService
  ]
})
export class BeneficiaryModule {
}
