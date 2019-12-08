import {Component, OnInit} from '@angular/core';
import {Store} from '@ngrx/store';
import {FeatureState} from '../store/beneficiary.state';

@Component({
  selector: 'app-beneficiary',
  templateUrl: './beneficiary.component.html',
  styleUrls: ['./beneficiary.component.css']
})
export class BeneficiaryComponent implements OnInit {

  constructor(private store: Store<FeatureState>) {
  }

  ngOnInit() {
    this.store.select((state: FeatureState) => state)
      .subscribe(value => console.log(value));
  }

}
