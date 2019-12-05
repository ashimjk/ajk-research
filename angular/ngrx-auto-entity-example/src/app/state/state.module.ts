import {NgModule} from '@angular/core';
import {NgrxAutoEntityModule} from '@briebug/ngrx-auto-entity';
import {EffectsModule} from '@ngrx/effects';
import {StoreModule} from '@ngrx/store';

import {appReducer} from './app.state';
import {StoreDevtoolsModule} from '@ngrx/store-devtools';
import {environment} from '../../environments/environment';

@NgModule({
  imports: [
    StoreModule.forRoot(appReducer, {
      runtimeChecks: {
        strictStateImmutability: true,
        strictActionImmutability: true,
        strictStateSerializability: true,
        strictActionSerializability: true,
      },
    }),
    EffectsModule.forRoot([]),
    NgrxAutoEntityModule.forRoot(),
    StoreDevtoolsModule.instrument({
      maxAge: 25,
      logOnly: environment.production
    })
  ]
})
export class StateModule {
}
