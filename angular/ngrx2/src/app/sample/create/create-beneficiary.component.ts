import {Component, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {FormGroupState} from 'ngrx-forms';
import {Store} from '@ngrx/store';
import {BENEFICIARY_CREATE_FORM_ID, BeneficiaryCreateForm} from '../create/store/beneficiary-create.state';
import {BENEFICIARY_FEATURE_NAME} from '../state/contants';

@Component({
  selector: 'app-create-beneficiary',
  template: `
    <div class="container mt-5">
      <div class="row">
        <div class="col-md-8">
          <form *ngIf="formState$" novalidate [ngrxFormState]="(formState$ | async)">
            <!--            <app-beneficiary-info [formState$]="formState$"></app-beneficiary-info>-->
            <app-verification-ids [formState$]="formState$"></app-verification-ids>
          </form>
        </div>
      </div>
    </div>
  `
})
export class CreateBeneficiaryComponent implements OnInit {

  formState$: Observable<FormGroupState<BeneficiaryCreateForm>>;
  submittedValue: BeneficiaryCreateForm;

  constructor(private store: Store<any>) {
    this.formState$ = this.store.select(state => state[BENEFICIARY_FEATURE_NAME][BENEFICIARY_CREATE_FORM_ID]);

    this.formState$.subscribe(value => {
      this.submittedValue = value.value;
    });
  }

  ngOnInit() {

  }

  submit() {
    alert(this.submittedValue);
  }

}
