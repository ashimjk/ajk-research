import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {ReactiveDrivenComponent} from './reactive-driven/reactive-driven.component';
import {TemplateDrivenComponent} from './template-driven/template-driven.component';

@NgModule({
  declarations: [
    AppComponent,
    ReactiveDrivenComponent,
    TemplateDrivenComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
