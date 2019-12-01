import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {ComponentOutletModule} from './component-outlet/component-outlet.module';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ComponentOutletModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
