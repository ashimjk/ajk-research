import {Component, OnInit} from '@angular/core';

import {Ingredient} from '../shared/ingredient.model';
import {Observable} from 'rxjs';
import {Store} from '@ngrx/store';
import {StartEdit} from './store/shopping-list-actions.type';
import {AppState} from '../store/app.state';
import {ShoppingListState} from './store/shopping-list.state';

@Component({
  selector: 'app-shopping-list',
  templateUrl: './shopping-list.component.html',
  styleUrls: ['./shopping-list.component.css']
})
export class ShoppingListComponent implements OnInit {
  shoppingList$: Observable<ShoppingListState>;
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
