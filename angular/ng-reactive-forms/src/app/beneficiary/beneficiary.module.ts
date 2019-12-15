import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {BeneficiaryShellComponent} from './container/beneficiary-shell/beneficiary-shell.component';
import {RouterModule, Routes} from '@angular/router';
import {ReactiveFormsModule} from '@angular/forms';
import {BeneficiaryListComponent} from './component/beneficiary-list/beneficiary-list.component';
import {BeneficiaryEditComponent} from './component/beneficiary-edit/beneficiary-edit.component';
import {BeneficiaryAddComponent} from './component/beneficiary-add/beneficiary-add.component';

const beneficiaryRoutes: Routes = [
  {
    path: 'beneficiary', component: BeneficiaryShellComponent, children: [
      {path: '', component: BeneficiaryListComponent},
      {path: 'create', component: BeneficiaryAddComponent}
    ]
  }
];

@NgModule({
  declarations: [
    BeneficiaryShellComponent,
    BeneficiaryListComponent,
    BeneficiaryEditComponent,
    BeneficiaryAddComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,

    RouterModule.forChild(beneficiaryRoutes)
  ],
  exports: [
    RouterModule
  ]
})
export class BeneficiaryModule {
}
