import {Ingredient} from '../shared/ingredient.model';
import {Injectable} from '@angular/core';
import {Subject, Subscription} from 'rxjs';

interface Test {
  test: () => void;
}

@Injectable({
  providedIn: 'root'
})
export class ShoppingListService {
  private ingredients: Ingredient[] = [
    new Ingredient('Apples', 5),
    new Ingredient('Tomatoes', 10),
  ];

  private ingredientSubject = new Subject<Ingredient[]>();
  private indexSubject = new Subject<number>();

  getIngredients(): Ingredient[] {
    return this.ingredients.slice();
  }

  getIngredient(index: number): Ingredient {
    return this.ingredients[index];
  }

  addIngredients(ingredients: Ingredient[]) {
    this.ingredients.push(...ingredients);

    this.publishIngredients(this.getIngredients());
  }

  addIngredient(ingredient: Ingredient) {
    this.ingredients.push(ingredient);

    this.publishIngredients(this.getIngredients());
  }

  updateIngredient(index: number, ingredient: Ingredient): void {
    this.ingredients[index].name = ingredient.name;
    this.ingredients[index].amount = ingredient.amount;

    this.publishIngredients(this.getIngredients());
  }

  deleteIngredient(index: number) {
    this.ingredients.splice(index, 1);

    this.publishIngredients(this.getIngredients());
  }

  publishIngredients(ingredients: Ingredient[]): void {
    return this.ingredientSubject.next(ingredients);
  }

  subscribeIngredients(data: (ingredients: Ingredient[]) => void): Subscription {
    return this.ingredientSubject.subscribe(ingredients => data(ingredients));
  }

  publishIngredientIndex(index: number): void {
    this.indexSubject.next(index);
  }

  subscribeIngredientIndex(data: (index: number) => void): Subscription {
    return this.indexSubject.subscribe(index => data(index));
  }
}
