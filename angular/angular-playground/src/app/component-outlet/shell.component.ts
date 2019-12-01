import {Component} from '@angular/core';
import {GreeterService} from './sample2/greeter.service';

@Component({
  selector: 'app-outlet-shell',
  template: `
    <app-component-outlet></app-component-outlet>
    <br>
    <app-complete-outlet></app-complete-outlet>
    <!--    <app-complete>Ashim Ashish</app-complete>-->
  `,
  providers: [GreeterService]
})
export class ShellComponent {

}
