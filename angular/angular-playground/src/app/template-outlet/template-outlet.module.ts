import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {NgTemplateOutletComponent} from './ng-template-outlet.component';


@NgModule({
  declarations: [NgTemplateOutletComponent],
  imports: [
    CommonModule
  ],
  exports: [NgTemplateOutletComponent]
})
export class TemplateOutletModule {
}
