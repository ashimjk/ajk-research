import {Component, Input, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-address-form3',
  template: `
    <h5>Address Form 3</h5>
    <div [formGroup]="addressGroup">
      <div class="form-group">
        <label for="city">City</label>
        <input id="city" class="form-control" type="text" formControlName="city"/>
      </div>

      <div class="form-group">
        <label for="country">Country</label>
        <input id="country" class="form-control" type="text" formControlName="country"/>
      </div>
    </div>
  `
})
export class AddressForm3Component implements OnInit {

  @Input() addressGroup: FormGroup;

  constructor() {
  }

  ngOnInit() {
    this.addressGroup.addControl('city', new FormControl('', Validators.required));
    this.addressGroup.addControl('country', new FormControl('', Validators.required));
  }

}
