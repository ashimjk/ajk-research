import {Component} from '@angular/core';
import {HelloWorldComponent} from './hello-world.component';

@Component({
  selector: 'app-component-outlet',
  template: '<ng-container *ngComponentOutlet="helloWorldComponent"></ng-container>',
  entryComponents: [HelloWorldComponent],
  inputs: ['name', 'id: number']
})
export class ComponentOutletComponent {
  helloWorldComponent = HelloWorldComponent;
  name: string;
  id: number;
}
