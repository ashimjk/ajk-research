import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {BeneficiaryComponent} from './component/beneficiary.component';
import {StoreModule} from '@ngrx/store';
import {ReactiveFormsModule} from '@angular/forms';
import {EffectsModule} from '@ngrx/effects';
import {BeneficiaryEffects} from './store/beneficiary.effects';
import {reducers} from './store';
import {beneficiaryFeatureKey} from './store/beneficiary.state';

@NgModule({
  declarations: [
    BeneficiaryComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    StoreModule.forFeature(beneficiaryFeatureKey, reducers),
    EffectsModule.forFeature([BeneficiaryEffects])
  ],
  exports: [
    BeneficiaryComponent
  ]
})
export class BeneficiaryModule {
}
