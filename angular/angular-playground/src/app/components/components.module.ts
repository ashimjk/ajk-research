import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {NeedsGreeterDirective, ViewProviderComponent} from './view-provider/view-provider.component';
import {ComponentShellComponent} from './component-shell.component';

@NgModule({
  declarations: [ViewProviderComponent, ComponentShellComponent, NeedsGreeterDirective],
  imports: [
    CommonModule
  ],
  exports: [ComponentShellComponent]
})
export class ComponentsModule {
}
