import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {CreateBeneficiaryComponent} from './create/create-beneficiary.component';
import {BfcBeneficiaryInfoComponent} from './create/basic-info/beneficiary-info/bfc-beneficiary-info.component';
import {BfcVerificationIdsComponent} from './create/basic-info/verification-ids/bfc-verification-ids.component';
import {FormsModule} from '@angular/forms';
import {NgrxFormsModule} from 'ngrx-forms';
import {StoreModule} from '@ngrx/store';
import {BENEFICIARY_FEATURE_NAME} from './state/contants';
import {featureReducer} from './state/feature.reducer';

@NgModule({
  declarations: [
    CreateBeneficiaryComponent,
    BfcBeneficiaryInfoComponent,
    BfcVerificationIdsComponent,
  ],
  imports: [
    CommonModule,
    FormsModule,
    NgrxFormsModule,
    StoreModule.forFeature(BENEFICIARY_FEATURE_NAME, featureReducer)
  ],
  exports: [
    CreateBeneficiaryComponent
  ]
})
export class SampleModule {
}
