import {Injectable} from '@angular/core';
import {Recipe} from './recipe.model';
import {Ingredient} from '../shared/ingredient.model';
import {ShoppingListService} from '../shopping-list/shopping-list.service';
import {Subject, Subscription} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RecipeService {

  private recipes: Recipe[] = [
    new Recipe(
      'Tasty Schnitzel',
      'A super-tasty Schnitzel - just awesome!',
      'https://upload.wikimedia.org/wikipedia/commons/7/72/Schnitzel.JPG',
      [
        new Ingredient('Meat', 1),
        new Ingredient('French Fries', 20)
      ]),
    new Recipe('Big Fat Burger',
      'What else you need to say?',
      'https://upload.wikimedia.org/wikipedia/commons/b/be/Burger_King_Angus_Bacon_%26_Cheese_Steak_Burger.jpg',
      [
        new Ingredient('Buns', 2),
        new Ingredient('Meat', 1)
      ])
  ];

  private recipesChanges = new Subject<Recipe[]>();

  constructor(private shoppingListService: ShoppingListService) {
  }

  getRecipes(): Recipe[] {
    return this.recipes.slice();
  }

  getRecipe(id: number): Recipe {
    return this.recipes[id];
  }

  addRecipe(recipe: Recipe) {
    this.recipes.push(recipe);
    this.publishChanges();
  }

  updateRecipe(index: number, recipe: Recipe) {
    this.recipes[index].name = recipe.name;
    this.recipes[index].imagePath = recipe.imagePath;
    this.recipes[index].description = recipe.description;
    this.recipes[index].ingredients = recipe.ingredients;

    this.publishChanges();
  }

  delete(index: number) {
    this.recipes.splice(index, 1);
    this.publishChanges();
  }

  addIngredientsToShoppingList(ingredients: Ingredient[]) {
    this.shoppingListService.addIngredients(ingredients);
  }

  private publishChanges() {
    this.recipesChanges.next(this.getRecipes());
  }

  subscribeChanges(next: (recipes: Recipe[]) => void): Subscription {
    return this.recipesChanges.subscribe((recipes: Recipe[]) => next(recipes));
  }
}
