import {Component} from '@angular/core';
import {Observable} from 'rxjs';
import {FormValue, INITIAL_STATE, SetSubmittedValueAction} from '../reducer';
import {FormGroupState, ResetAction, SetValueAction} from 'ngrx-forms';
import {select, Store} from '@ngrx/store';
import {AppState} from '../../store/app.state';
import {map, take} from 'rxjs/operators';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css']
})
export class FormComponent {

  formState$: Observable<FormGroupState<FormValue>>;

  constructor(private store: Store<AppState>) {
    this.formState$ = store.pipe(select(s => s.myForm));
  }

  reset() {
    this.store.dispatch(new SetValueAction(INITIAL_STATE.id, INITIAL_STATE.value));
    this.store.dispatch(new ResetAction(INITIAL_STATE.id));
  }

  submit() {
    this.formState$.pipe(
      take(1),
      map(fs => new SetSubmittedValueAction(fs.value)),
    ).subscribe(this.store);
  }

}
