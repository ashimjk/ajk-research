import {Component} from '@angular/core';

@Component({
  selector: 'app-ng-template-outlet',
  template: `
    <ng-container *ngTemplateOutlet="greet"></ng-container>
    <hr>
    <ng-container *ngTemplateOutlet="eng; context: myContext"></ng-container>
    <hr>
    <ng-container *ngTemplateOutlet="svk; context: myContext"></ng-container>
    <hr>

    <ng-template #greet><span>Hello</span></ng-template>
    <ng-template #eng let-name><span>Hello {{name}}!</span></ng-template>
    <ng-template #svk let-greeting="localGreeting"><span>{{greeting}} Ashim!</span></ng-template>
  `
})
export class NgTemplateOutletComponent {
  // Using the key $implicit in the context object will set its value as default.
  myContext = {$implicit: 'World', localGreeting: 'Hello'};
}
