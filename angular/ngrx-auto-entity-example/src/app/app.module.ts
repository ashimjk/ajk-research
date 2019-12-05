import {HttpClientModule} from '@angular/common/http';
import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from './app.component';
import {CustomersComponent} from './components/customers/customers.component';
import {StateModule} from './state';
import {Customer} from './models';
import {EntityService} from './services';
import {CustomerListComponent} from './components/customers/customer-list/customer-list.component';
import {CustomerDetailComponent} from './components/customers/customer-detail/customer-detail.component';
import {CustomerEditFormComponent} from './components/customers/customer-edit-form/customer-edit-form.component';
import {CustomerEditComponent} from './components/customers/customer-edit/customer-edit.component';

@NgModule({
  bootstrap: [
    AppComponent
  ],
  declarations: [
    AppComponent,
    CustomersComponent,
    CustomerListComponent,
    CustomerEditFormComponent,
    CustomerDetailComponent,
    CustomerEditComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    StateModule,
  ],
  providers: [
    {provide: Customer, useClass: EntityService}
  ]
})
export class AppModule {
}
