import {Attribute, Directive} from '@angular/core';

@Directive({
  // tslint:disable-next-line:directive-selector
  selector: 'appInputAttr'
})
export class InputAttrDirective {

  // always injected as a constant string literal
  constructor(@Attribute('type') type: string) {
    console.log(type);
  }

}
