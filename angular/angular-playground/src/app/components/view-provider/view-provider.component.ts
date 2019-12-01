import {Attribute, Component, Directive, HostBinding} from '@angular/core';

class Greeter {
  greet(name: string) {
    return 'Hello ' + name + '!';
  }
}

@Directive({
  selector: '[appNeedsGreeter]'
})
export class NeedsGreeterDirective {
  greeter: Greeter;
  @HostBinding('innerText') text: string;

  constructor(@Attribute('value') value, greeter: Greeter) {
    this.greeter = greeter;
    this.text = greeter.greet('ashim');
  }
}

@Component({
  selector: 'app-view-provider',
  viewProviders: [
    Greeter
  ],
  template: '<div appNeedsGreeter></div>'
})
export class ViewProviderComponent {
}
