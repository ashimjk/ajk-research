import {Injectable} from '@angular/core';
import {RecipeService} from '../recipes/recipe.service';
import {HttpClient} from '@angular/common/http';
import {Recipe} from '../recipes/recipe.model';

@Injectable({
  providedIn: 'root'
})
export class DataStorageService {

  constructor(private recipeService: RecipeService,
              private http: HttpClient) {
  }

  storeRecipes() {
    return this.http.put('https://recipe-book-15e0f.firebaseio.com/recipes.json', this.recipeService.getRecipes());
  }

  getRecipes() {
    this.http.get('https://recipe-book-15e0f.firebaseio.com/recipes.json')
      .subscribe(
        (recipes: Recipe[]) => {
          recipes.forEach(recipe => {
            if (!recipe.ingredients) {
              recipe.ingredients = [];
            }
          });

          this.recipeService.setRecipes(recipes);
        },
        (error) => console.error(error)
      );
  }
}
