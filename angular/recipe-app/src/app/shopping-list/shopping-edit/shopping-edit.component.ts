import {Component, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {Ingredient} from '../../shared/ingredient.model';
import {NgForm} from '@angular/forms';
import {Subscription} from 'rxjs';
import {Store} from '@ngrx/store';
import {AddIngredient, DeleteIngredient, StopEdit, UpdateIngredient} from '../store/shopping-list-actions.type';
import {AppState} from '../../store/app.state';

@Component({
  selector: 'app-shopping-edit',
  templateUrl: './shopping-edit.component.html',
  styleUrls: ['./shopping-edit.component.css']
})
export class ShoppingEditComponent implements OnInit, OnDestroy {

  @ViewChild('shoppingForm', {static: false}) shoppingForm: NgForm;
  private indexSubscription: Subscription;
  private editedItem: Ingredient;
  editMode: boolean;

  constructor(private store: Store<AppState>) {
  }

  ngOnInit(): void {
    this.indexSubscription = this.store.select('shoppingList')
      .subscribe(
        data => {
          if (data.editedIngredientIndex > -1) {
            this.editedItem = data.editedIngredient;
            this.editMode = true;

            this.shoppingForm.setValue({
              name: this.editedItem.name,
              amount: this.editedItem.amount
            });
          } else {
            this.editedItem = null;
            this.editMode = false;
          }
        }
      );
  }

  onSubmit() {
    const value = this.shoppingForm.value;
    const newIngredient = new Ingredient(value.name, value.amount);

    if (this.editMode) {
      this.store.dispatch(new UpdateIngredient(newIngredient));
    } else {
      this.store.dispatch(new AddIngredient(newIngredient));
    }

    this.onClear();
  }

  ngOnDestroy(): void {
    this.indexSubscription.unsubscribe();
    this.store.dispatch(new StopEdit());
  }

  onClear() {
    this.editMode = false;
    this.shoppingForm.resetForm();
  }

  onDelete() {
    this.store.dispatch(new DeleteIngredient());
    this.onClear();
  }
}
