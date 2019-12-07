import {Component, OnInit} from '@angular/core';
import {Recipe} from '../recipe.model';
import {ActivatedRoute, Params, Router} from '@angular/router';
import {Store} from '@ngrx/store';
import {AddIngredients} from '../../shopping-list/store/shopping-list-actions.type';
import {RecipeFeatureState, RecipeState} from '../store/recipe.state';
import {Observable} from 'rxjs';
import {map, take} from 'rxjs/operators';
import {DeleteRecipe} from '../store/recipe.actions';

@Component({
  selector: 'app-recipe-detail',
  templateUrl: './recipe-detail.component.html',
  styleUrls: ['./recipe-detail.component.css']
})
export class RecipeDetailComponent implements OnInit {

  private id: number;
  recipeState$: Observable<Recipe>;

  constructor(private activatedRoute: ActivatedRoute,
              private router: Router,
              private store: Store<RecipeFeatureState>) {
  }

  ngOnInit(): void {
    this.id = +this.activatedRoute.snapshot.params.id;
    this.activatedRoute.params.subscribe(
      (params: Params) => {
        this.id = +params.id;
        this.recipeState$ = this.store.select('recipes').pipe(
          map((recipeState: RecipeState) => recipeState.recipes[this.id])
        );
      }
    );
  }

  onAddToShoppingList() {
    this.store.select('recipes')
      .pipe(take(1))
      .subscribe((recipeState: RecipeState) => this.store.dispatch(
        new AddIngredients(recipeState.recipes[this.id].ingredients)
      ));
  }

  onDelete() {
    this.store.dispatch(new DeleteRecipe(this.id));
    this.router.navigate(['/recipes']);
  }
}
