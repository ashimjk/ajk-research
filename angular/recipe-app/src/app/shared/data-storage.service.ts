import {Injectable} from '@angular/core';
import {RecipeService} from '../recipes/recipe.service';
import {HttpClient} from '@angular/common/http';
import {Recipe} from '../recipes/recipe.model';
import {AuthService} from '../auth/auth.service';

@Injectable({
  providedIn: 'root'
})
export class DataStorageService {

  constructor(private recipeService: RecipeService,
              private http: HttpClient,
              private authService: AuthService) {
  }

  storeRecipes() {
    const token = this.authService.getToken();
    return this.http.put('https://recipe-book-15e0f.firebaseio.com/recipes.json?auth=' + token, this.recipeService.getRecipes());
  }

  getRecipes() {
    const token = this.authService.getToken();

    this.http.get('https://recipe-book-15e0f.firebaseio.com/recipes.json?auth=' + token)
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
