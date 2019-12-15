import {Component, Input} from '@angular/core';
import {FormGroup} from '@angular/forms';

@Component({
  selector: 'app-address-form',
  template: `
    <h5>Address Form</h5>
    <div formGroupName="address">
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
export class AddressFormComponent {

  @Input() private addressFormGroup: FormGroup;

}
