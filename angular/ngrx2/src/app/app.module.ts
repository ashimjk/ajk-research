import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {StoreModule} from '@ngrx/store';
import {StoreDevtoolsModule} from '@ngrx/store-devtools';
import {environment} from '../environments/environment';
import {UserComponent} from './users/component/user.component';
import {ReactiveFormsModule} from '@angular/forms';
import {reducers} from './store/app.reducers';
import {EffectsModule} from '@ngrx/effects';
import {UserEffects} from './users/store/user.effects';
import {BeneficiaryModule} from './beneficiary/beneficiary.module';

@NgModule({
  declarations: [
    AppComponent,
    UserComponent
  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,

    BeneficiaryModule,

    StoreModule.forRoot(reducers),
    StoreDevtoolsModule.instrument({
      maxAge: 25,
      logOnly: environment.production
    }),
    EffectsModule.forRoot([UserEffects])
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
