import {initialRecipeState, RecipeState} from './recipe.state';
import {ADD_RECIPE, DELETE_RECIPE, RecipeActionsType, SET_RECIPES, UPDATE_RECIPE} from './recipe.actions';

export function recipeReducer(state: RecipeState = initialRecipeState, action: RecipeActionsType) {

  switch (action.type) {
    case SET_RECIPES:
      return {
        ...state,
        recipes: [...action.payload]
      };

    case ADD_RECIPE:
      return {
        ...state,
        recipes: [...state.recipes, action.payload]
      };

    case UPDATE_RECIPE: {
      const recipe = state.recipes[action.payload.index];
      const updateRecipe = {
        ...recipe,
        ...action.payload.updatedRecipe
      };
      const recipes = [...state.recipes];
      recipes[action.payload.index] = updateRecipe;
      return {
        ...state,
        recipes
      };
    }

    case DELETE_RECIPE: {
      const recipes = [...state.recipes];
      recipes.splice(action.payload, 1);
      return {
        ...state,
        recipes
      };
    }

    default:
      return state;
  }
}
