import {Component} from '@angular/core';
import {Observable} from 'rxjs';
import {select, Store} from '@ngrx/store';
import {decrement, increment, reset} from '../state/counter.action';
import {AppState, counterSelector, FEATURE_APP_STATE, featureSelector} from '../state/counter.reducer';

@Component({
  selector: 'app-my-counter',
  templateUrl: './my-counter.component.html',
  styleUrls: ['./my-counter.component.css']
})
export class MyCounterComponent {
  count$: Observable<number>;
  appState$: Observable<AppState>;
  count = 0;

  constructor(private store: Store<AppState>) {
    // this.appState$ = this.store.pipe(select(state => {
    //   return state['appState'];
    // }));

    // this.appState$ = this.store.pipe(select(FEATURE_APP_STATE));
    // this.count$ = this.store.pipe(select('count'));

    this.appState$ = this.store.pipe(select(featureSelector));
    this.count$ = this.store.pipe(select(counterSelector));
  }

  increment() {
    this.store.dispatch(increment());
  }

  decrement() {
    this.store.dispatch(decrement());
  }

  reset() {
    this.store.dispatch(reset());
  }
}
