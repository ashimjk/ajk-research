import {NgModule} from '@angular/core';
import {StoreModule} from '@ngrx/store';
import {MyCounterComponent} from './my-counter/my-counter.component';
import {FEATURE_APP_STATE, appReducer} from './state/counter.reducer';
import {CommonModule} from '@angular/common';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

@NgModule({
  declarations: [
    MyCounterComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    StoreModule.forFeature(FEATURE_APP_STATE, appReducer),
  ],
  exports: [MyCounterComponent]
})
export class CounterModule {
}
