import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {InputAttrDirective} from './input-attr.directive';
import {DirectiveShellComponent} from './directive-shell.component';

@NgModule({
  declarations: [InputAttrDirective, DirectiveShellComponent],
  imports: [
    CommonModule
  ],
  exports: [DirectiveShellComponent]
})
export class DirectiveAttributeModule {
}
