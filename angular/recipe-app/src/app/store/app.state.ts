import {ShoppingListState} from '../shopping-list/store/shopping-list.state';
import {AuthState} from '../auth/store/auth.state';

export interface AppState {
  shoppingList: ShoppingListState;
  auth: AuthState;
}
