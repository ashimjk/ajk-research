import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {DropdownDirective} from './dropdown.directive';

@NgModule({
  declarations: [
    DropdownDirective
  ],
  imports: [
    CommonModule
  ],
  exports: [
    CommonModule,
    DropdownDirective
  ]
})
export class SharedModule {
}
