import {Component, Input, OnInit} from '@angular/core';
import {FormGroupState} from "ngrx-forms";
import {Observable} from 'rxjs';
import {CorpaySelectItem} from "@corpay/controls/lib/model";
import {BeneficiaryCreateForm} from "../../store/beneficiary-create.state";

@Component({
  selector: 'beneficiary-info',
  template: `
    <div class="row">
      <div class="col-xl-4">
        <corpay-input-text [label]="'Full Name'"
                           [formControlState]="(formState$ | async).controls.fullName"
                           [required]="true">
        </corpay-input-text>
      </div>

      <div class="col-xl-4">
        <corpay-select-input [required]="true" label="Type"
                             [items]="items"
                             [formControlState]="(formState$ | async).controls.beneficiaryType">
        </corpay-select-input>
      </div>

      <!--                             TODO: -->
<!--      <div class="col-xl-4">-->
<!--        <corpay-select-input [required]="true" label="Verify Identity By"-->
<!--                             [formControlState]="(formState$ | async).controls.idTypes">-->
<!--        </corpay-select-input>-->
<!--      </div>-->
    </div>
  `,
  styleUrls: ['./bfc-beneficiary-info.component.scss']
})
export class BfcBeneficiaryInfoComponent implements OnInit {

  @Input() formState$: Observable<FormGroupState<BeneficiaryCreateForm>>;
  items: CorpaySelectItem[];

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
