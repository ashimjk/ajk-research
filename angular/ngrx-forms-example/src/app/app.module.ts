import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {StoreModule} from '@ngrx/store';
import {MyComponent} from './my-component/my.component';
import {StoreDevtoolsModule} from '@ngrx/store-devtools';
import {environment} from '../environments/environment';
import {MyCounterComponent} from './my-counter/my-counter.component';
import {formReducer} from './my-component/state/my.reducer';
import {NgrxFormsModule} from 'ngrx-forms';

@NgModule({
  declarations: [
    AppComponent,
    MyComponent,
    MyCounterComponent
  ],
  imports: [
    BrowserModule,
    NgrxFormsModule,
    // StoreModule.forRoot({count: counterReducer, myComponent: appReducer}),
    StoreModule.forRoot({form: formReducer}),
    StoreDevtoolsModule.instrument({
      maxAge: 25,
      logOnly: environment.production
    })
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
