import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {RouterModule, Routes} from '@angular/router';
import {BeneficiaryModule} from './beneficiary/beneficiary.module';

const appRoutes: Routes = [
  {path: '', redirectTo: '/beneficiary', pathMatch: 'full'}
];

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(appRoutes),

    BeneficiaryModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
