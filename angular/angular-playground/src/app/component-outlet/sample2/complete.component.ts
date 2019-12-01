import {Component} from '@angular/core';
import {GreeterService} from './greeter.service';

@Component({
  selector: 'app-complete',
  template: `Complete:
  <ng-content></ng-content>
  <ng-content></ng-content>{{ greeter.suffix }}`
})
export class CompleteComponent {

  constructor(public greeter: GreeterService) {
  }

}
