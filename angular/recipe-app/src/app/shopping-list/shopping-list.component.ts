import {Component, OnInit} from '@angular/core';

import {Ingredient} from '../shared/ingredient.model';
import {Observable} from 'rxjs';
import {Store} from '@ngrx/store';
import {AppState, State} from './store/shopping-list.reducers';
import {StartEdit} from './store/shopping-list.actions';

@Component({
  selector: 'app-shopping-list',
  templateUrl: './shopping-list.component.html',
  styleUrls: ['./shopping-list.component.css']
})
export class ShoppingListComponent implements OnInit {
  shoppingList$: Observable<State>;
  ingredients$: Observable<Ingredient[]>;

  constructor(private store: Store<AppState>) {
  }

  ngOnInit() {
    this.shoppingList$ = this.store.select('shoppingList');
    this.ingredients$ = this.store.select(state => state.shoppingList.ingredients);
  }

  onEdit(index: number) {
    this.store.dispatch(new StartEdit(index));
  }

}
