import {Injectable} from '@angular/core';
import {HttpClient, HttpRequest} from '@angular/common/http';
import {Recipe} from '../recipes/recipe.model';
import {map} from 'rxjs/operators';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DataStorageService {

  constructor(private http: HttpClient) {
  }

  storeRecipes(recipes: Recipe[]) {
    const request = new HttpRequest(
      'PUT',
      'https://recipe-book-15e0f.firebaseio.com/recipes.json',
      recipes,
      {
        reportProgress: true,
        // Will be provided from AuthInterceptor
        // params: new HttpParams().set('auth', token)
      });

    return this.http.request(request);
  }

  storeRecipes2() {
    // return this.http.put('https://recipe-book-15e0f.firebaseio.com/recipes.json?auth=' + token, this.recipeService.getRecipes());

    // return this.http.put(
    //   'https://recipe-book-15e0f.firebaseio.com/recipes.json?auth=' + token,
    //   this.recipeService.getRecipes(),
    //   {
    //     observe: 'events'
    //   }
    // );
  }

  getRecipes(): Observable<Recipe[]> {
    // this.http.get<Recipe[]>('https://recipe-book-15e0f.firebaseio.com/recipes.json?auth=' + token)
    return this.http.get('https://recipe-book-15e0f.firebaseio.com/recipes.json')
      .pipe(
        map(
          (recipes: Recipe[]) => {
            recipes.forEach(recipe => {
              if (!recipe.ingredients) {
                recipe.ingredients = [];
              }
            });
            return recipes;
          }
        )
      );
  }

  getRecipes2() {
    this.http.get('https://recipe-book-15e0f.firebaseio.com/recipes.json',
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
}
