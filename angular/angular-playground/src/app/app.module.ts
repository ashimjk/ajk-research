import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {ComponentOutletModule} from './component-outlet/component-outlet.module';
import {TemplateOutletModule} from './template-outlet/template-outlet.module';
import {DirectiveAttributeModule} from './directive-attribute/directive-attribute.module';
import {ComponentsModule} from './components/components.module';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ComponentOutletModule,
    TemplateOutletModule,
    DirectiveAttributeModule,
    ComponentsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
