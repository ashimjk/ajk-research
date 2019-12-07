import {Injectable} from '@angular/core';
import {RecipeService} from '../recipes/recipe.service';
import {HttpClient, HttpRequest} from '@angular/common/http';
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
    // return this.http.put('https://recipe-book-15e0f.firebaseio.com/recipes.json?auth=' + token, this.recipeService.getRecipes());

    // return this.http.put(
    //   'https://recipe-book-15e0f.firebaseio.com/recipes.json?auth=' + token,
    //   this.recipeService.getRecipes(),
    //   {
    //     observe: 'events'
    //   }
    // );

    const request = new HttpRequest(
      'PUT',
      'https://recipe-book-15e0f.firebaseio.com/recipes.json',
      this.recipeService.getRecipes(),
      {
        reportProgress: true,
        // Will be provided from AuthInterceptor
        // params: new HttpParams().set('auth', token)
      });

    return this.http.request(request);
  }

  getRecipes2() {
    const token = this.authService.getToken();

    this.http.get('https://recipe-book-15e0f.firebaseio.com/recipes.json?auth=' + token,
      {
        observe: 'body',
        responseType: 'json'
      })
      .subscribe(
        (recipes) => {
          console.log(recipes);
        },
        (error) => console.error(error)
      );
  }

  getRecipes() {
    const token = this.authService.getToken();

    // this.http.get<Recipe[]>('https://recipe-book-15e0f.firebaseio.com/recipes.json?auth=' + token)
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
