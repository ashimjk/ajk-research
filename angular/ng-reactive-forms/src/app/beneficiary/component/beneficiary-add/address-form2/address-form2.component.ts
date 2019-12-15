import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-address-form2',
  template: `
    <h5>Address Form 2</h5>
    <div [formGroup]="address">
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
export class AddressForm2Component implements OnInit {

  @Output() formGroup = new EventEmitter<FormGroup>();

  address: FormGroup;

  constructor(private fb: FormBuilder) {
  }

  ngOnInit() {
    this.address = this.fb.group({
      city: new FormControl('', Validators.required),
      country: new FormControl('', Validators.required)
    });

    this.formGroup.emit(this.address);
  }

}
