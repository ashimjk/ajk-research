import {Component, OnInit} from '@angular/core';
import {Store} from '@ngrx/store';
import {Beneficiary, FeatureState, selectAllBeneficiary, selectError} from '../store/beneficiary.state';
import {Observable} from 'rxjs';
import {FormControl, FormGroup} from '@angular/forms';
import {createBeneficiary, loadBeneficiaries} from '../store/beneficiary.actions';

@Component({
  selector: 'app-beneficiary',
  templateUrl: './beneficiary.component.html',
  styleUrls: ['./beneficiary.component.css']
})
export class BeneficiaryComponent implements OnInit {

  beneficiaries$: Observable<Beneficiary[]>;
  error$: Observable<string>;

  beneficiaryForm: FormGroup;

  constructor(private store: Store<FeatureState>) {
  }

  ngOnInit() {
    this.store.dispatch(loadBeneficiaries());

    this.beneficiaries$ = this.store.select(selectAllBeneficiary);
    this.error$ = this.store.select(selectError);

    this.beneficiaryForm = new FormGroup({
      reference: new FormControl(),
      amount: new FormControl()
    });
  }

  onSubmit() {
    this.store.dispatch(createBeneficiary({beneficiary: this.beneficiaryForm.value}));
  }
}
