import {Injectable} from '@angular/core';
import {Actions, Effect, ofType} from '@ngrx/effects';
import {FETCH_RECIPES, SET_RECIPES, STORE_RECIPES} from './recipe.actions';
import {map, switchMap, withLatestFrom} from 'rxjs/operators';
import {RecipeFeatureState} from './recipe.state';
import {Store} from '@ngrx/store';
import {Recipe} from '../recipe.model';
import {DataStorageService} from '../../shared/data-storage.service';

@Injectable()
export class RecipeEffects {

  constructor(
    private store: Store<RecipeFeatureState>,
    private actions$: Actions,
    private dataStorage: DataStorageService) {
  }

  @Effect()
  fetchRecipes$ = this.actions$.pipe(
    ofType(FETCH_RECIPES),
    switchMap(action => this.dataStorage.getRecipes()),
    map((recipes: Recipe[]) => {
      return {
        type: SET_RECIPES,
        payload: recipes
      };
    })
  );

  @Effect({dispatch: false})
  storeRecipes$ = this.actions$.pipe(
    ofType(STORE_RECIPES),
    withLatestFrom(this.store.select('recipes')),
    switchMap(([action, state]) => this.dataStorage.storeRecipes(state.recipes)),
  );
}
