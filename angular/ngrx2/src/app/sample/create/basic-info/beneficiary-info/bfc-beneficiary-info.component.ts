import {Component, Input, OnInit} from '@angular/core';
import {FormGroupState} from 'ngrx-forms';
import {Observable} from 'rxjs';
import {BeneficiaryCreateForm} from '../../store/beneficiary-create.state';

@Component({
  selector: 'app-beneficiary-info',
  template: `
    <div class="row">
      <div class="col-xl-4">
        <label for="fullName">Full Name</label><br>
        <input type="text" id="fullName" [ngrxFormControlState]="(formState$ | async).controls.fullName">
      </div>

      <div class="col-xl-4">
        <label for="beneficiaryType">Beneficiary Type</label>
        <select name="beneficiaryType" id="beneficiaryType" class="custom-select"
                [ngrxFormControlState]="(formState$ | async).controls.beneficiaryType">
          <option *ngFor="let item of items" [value]="item.value">{{ item.label }}</option>
        </select>
      </div>
    </div>
  `
})
export class BfcBeneficiaryInfoComponent implements OnInit {

  @Input() formState$: Observable<FormGroupState<BeneficiaryCreateForm>>;
  items: { label: string, value: string }[];

  constructor() {
  }

  ngOnInit() {
    this.items = [{
      label: 'Corporate',
      value: 'CORPORATE'
    },
      {
        label: 'Individual',
        value: 'INDIVIDUAL'
      }];
  }

}
