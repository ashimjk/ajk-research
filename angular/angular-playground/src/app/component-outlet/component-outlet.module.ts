import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {HelloWorldComponent} from './sample1/hello-world.component';
import {ComponentOutletComponent} from './sample1/component-outlet.component';
import {ShellComponent} from './shell.component';
import {CompleteComponent} from './sample2/complete.component';
import {CompleteOutletComponent} from './sample2/complete-outlet.component';

@NgModule({
  declarations: [HelloWorldComponent, ComponentOutletComponent, ShellComponent, CompleteComponent, CompleteOutletComponent],
  imports: [
    CommonModule
  ],
  exports: [
    ShellComponent
  ],
  entryComponents: [CompleteComponent]
})
export class ComponentOutletModule {
}
