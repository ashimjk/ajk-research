import {Injectable} from '@angular/core';
import {Observable, of} from 'rxjs';
import {Beneficiary} from './store/beneficiary.state';

@Injectable({
  providedIn: 'root'
})
export class BeneficiaryService {

  constructor() {
  }

  getBeneficiaries(): Observable<Beneficiary[]> {
    return of([
      {
        reference: '#ref1',
        amount: 120.00
      },
      {
        reference: '#ref2',
        amount: 305.00
      },
      {
        reference: '#ref3',
        amount: 230.00
      }
    ]);
  }

  addBeneficiary(beneficiary: Beneficiary): Observable<Beneficiary> {
    return of(beneficiary);
  }
}
