import {
  ADD_INGREDIENT,
  ADD_INGREDIENTS,
  DELETE_INGREDIENT,
  ShoppingListActionsType,
  START_EDIT,
  STOP_EDIT,
  UPDATE_INGREDIENT
} from './shopping-list-actions.type';
import {initialShoppingListState, ShoppingListState} from './shopping-list.state';


export function shoppingListReducer(state: ShoppingListState = initialShoppingListState, action: ShoppingListActionsType) {

  switch (action.type) {
    case ADD_INGREDIENT:
      return {
        ...state,
        ingredients: [...state.ingredients, action.payload]
      };

    case ADD_INGREDIENTS:
      return {
        ...state,
        ingredients: [...state.ingredients, ...action.payload]
      };

    case UPDATE_INGREDIENT: {
      const ingredient = state.ingredients[state.editedIngredientIndex];
      const updatedIngredient = {
        ...ingredient,
        ...action.payload
      };
      const ingredients = [...state.ingredients];
      ingredients[state.editedIngredientIndex] = updatedIngredient;
      return {
        ...state,
        ingredients,
        editedIngredient: null,
        editedIngredientIndex: -1
      };
    }

    case DELETE_INGREDIENT: {
      const ingredients = [...state.ingredients];
      ingredients.splice(state.editedIngredientIndex, 1);
      return {
        ...state,
        ingredients,
        editedIngredient: null,
        editedIngredientIndex: -1
      };
    }

    case START_EDIT:
      return {
        ...state,
        editedIngredient: {...state.ingredients[action.payload]},
        editedIngredientIndex: action.payload
      };

    case STOP_EDIT:
      return {
        ...state,
        editedIngredient: null,
        editedIngredientIndex: -1
      };

    default:
      return state;
  }

}
