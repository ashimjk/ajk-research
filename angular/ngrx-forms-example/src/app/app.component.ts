import {Component} from '@angular/core';
import {Observable} from 'rxjs';
import {MyFormValue} from './my-component/state/my.reducer';
import {Store} from '@ngrx/store';
import {FormGroupState} from 'ngrx-forms';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  formState$: Observable<FormGroupState<MyFormValue>>;

  constructor(store: Store<any>) {
    this.formState$ = store.select(state => state.form);
  }
}
