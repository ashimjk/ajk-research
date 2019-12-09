import {Injectable} from '@angular/core';
import {Actions, Effect, ofType} from '@ngrx/effects';
import {catchError, map, mergeMap} from 'rxjs/operators';
import {of} from 'rxjs';
import {BeneficiaryService} from '../beneficiary.service';
import {
  createBeneficiary,
  createBeneficiaryFail,
  createBeneficiarySuccess,
  loadBeneficiaries,
  loadBeneficiariesFail,
  loadBeneficiariesSuccess
} from './beneficiary.actions';

@Injectable()
export class BeneficiaryEffects {

  @Effect()
  loadBeneficiaries$ = this.action$.pipe(
    ofType(loadBeneficiaries),
    mergeMap(() => this.beneficiaryService.getBeneficiaries()
      .pipe(
        map(beneficiaries => loadBeneficiariesSuccess({beneficiaries})),
        catchError(() => of(loadBeneficiariesFail({error: 'Records not found!!!'})))
      )
    )
  );

  @Effect()
  addBeneficiary$ = this.action$.pipe(
    ofType(createBeneficiary),
    mergeMap(action => this.beneficiaryService.addBeneficiary(action.beneficiary)
      .pipe(
        map(beneficiary => createBeneficiarySuccess({beneficiary})),
        catchError(error => of(createBeneficiaryFail({error: 'Failed to add beneficiary!!!'})))
      )
    )
  );

  constructor(private action$: Actions,
              private beneficiaryService: BeneficiaryService) {
  }

}
