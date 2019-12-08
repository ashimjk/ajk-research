import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {BeneficiaryComponent} from './component/beneficiary.component';
import {StoreModule} from '@ngrx/store';
import {reducers} from './store/beneficiary.reducers';

@NgModule({
  declarations: [
    BeneficiaryComponent
  ],
  imports: [
    CommonModule,
    StoreModule.forFeature('beneficiaryFeature', reducers)
  ],
  exports: [
    BeneficiaryComponent
  ]
})
export class BeneficiaryModule {
}
