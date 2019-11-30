import {Component, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {Ingredient} from '../../shared/ingredient.model';
import {ShoppingListService} from '../shopping-list.service';
import {NgForm} from '@angular/forms';
import {Subscription} from 'rxjs';

@Component({
  selector: 'app-shopping-edit',
  templateUrl: './shopping-edit.component.html',
  styleUrls: ['./shopping-edit.component.css']
})
export class ShoppingEditComponent implements OnInit, OnDestroy {

  @ViewChild('shoppingForm', {static: false}) shoppingForm: NgForm;
  private indexSubscription: Subscription;
  private ingredientIndex: number;
  private editMode: boolean;

  constructor(private shoppingListService: ShoppingListService) {
  }

  ngOnInit(): void {
    this.indexSubscription = this.shoppingListService.subscribeIngredientIndex(index => {
      this.editMode = true;
      this.ingredientIndex = index;
      const ingredient = this.shoppingListService.getIngredient(index);

      this.shoppingForm.setValue({
        name: ingredient.name,
        amount: ingredient.amount
      });
    });
  }

  onSubmit() {
    const value = this.shoppingForm.value;
    const newIngredient = new Ingredient(value.name, value.amount);

    if (this.editMode) {
      this.shoppingListService.updateIngredient(this.ingredientIndex, newIngredient);
    } else {
      this.shoppingListService.addIngredient(newIngredient);
    }

    this.onClear();
  }

  ngOnDestroy(): void {
    this.indexSubscription.unsubscribe();
  }

  onClear() {
    this.editMode = false;
    this.ingredientIndex = -1;
    this.shoppingForm.resetForm();
  }

  onDelete() {
    this.shoppingListService.deleteIngredient(this.ingredientIndex);
    this.onClear();
  }
}
