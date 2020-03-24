import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './components/app/app.component';
import {GridComponent} from './components/beneficiary/grid/grid.component';
import {CreateBeneficiaryComponent} from './components/beneficiary/create/create-beneficiary.component';
import {EditBeneficiaryComponent} from './components/beneficiary/edit/edit-beneficiary.component';
import {RouterModule, Routes} from '@angular/router';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

const appRoutes: Routes = [
  {path: 'list', component: GridComponent},
  {path: 'edit/:id', component: EditBeneficiaryComponent},
  {path: 'create', component: CreateBeneficiaryComponent},
  {path: '', component: GridComponent},
  {path: '*', component: GridComponent},
];

@NgModule({
  declarations: [
    AppComponent,
    GridComponent,
    CreateBeneficiaryComponent,
    EditBeneficiaryComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    RouterModule.forRoot(appRoutes),
    BrowserAnimationsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
