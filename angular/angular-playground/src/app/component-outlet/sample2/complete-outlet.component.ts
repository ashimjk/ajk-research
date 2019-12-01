import {Component, Injector} from '@angular/core';
import {GreeterService} from './greeter.service';
import {CompleteComponent} from './complete.component';

@Component({
  selector: 'app-complete-outlet',
  template: `
    <!--    <ng-container *ngComponentOutlet="completeComponent;-->
    <!--                                      injector: myInjector;-->
    <!--                                      content: myContent"></ng-container>-->

    <ng-container *ngComponentOutlet="completeComponent;
                                      content: myContent"></ng-container>
  `
})
export class CompleteOutletComponent {

  // This field is necessary to expose CompleteComponent to the template.
  completeComponent = CompleteComponent;
  myInjector: Injector;

  myContent = [
    [document.createTextNode('Ashim')],
    [document.createTextNode('Ashish')]
  ];

  constructor(injector: Injector) {
    this.myInjector = Injector.create({
      providers: [
        {
          provide: GreeterService, deps: []
        }
      ],
      parent: injector
    });
  }

}
