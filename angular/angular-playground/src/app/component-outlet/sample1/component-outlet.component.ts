import {Component} from '@angular/core';
import {HelloWorldComponent} from './hello-world.component';

@Component({
  selector: 'app-component-outlet',
  template: '<ng-container *ngComponentOutlet="helloWorldComponent"></ng-container>'
})
export class ComponentOutletComponent {
  helloWorldComponent = HelloWorldComponent;
}
