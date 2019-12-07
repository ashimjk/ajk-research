import {Component, OnInit} from '@angular/core';
import {AppState} from '../../store/app.state';
import {Store} from '@ngrx/store';
import {Observable} from 'rxjs';
import {AuthState} from '../../auth/store/auth.state';
import {LogOut} from '../../auth/store/auth.actions';
import {FetchRecipes, StoreRecipes} from '../../recipes/store/recipe.actions';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html'
})
export class HeaderComponent implements OnInit {

  authState$: Observable<AuthState>;

  constructor(private store: Store<AppState>) {
  }

  ngOnInit(): void {
    this.authState$ = this.store.select('auth');
  }

  onFetchRecipes() {
    this.store.dispatch(new FetchRecipes());
  }

  onStoreRecipes() {
    this.store.dispatch(new StoreRecipes());
  }

  onLogOut() {
    this.store.dispatch(new LogOut());
  }

}
