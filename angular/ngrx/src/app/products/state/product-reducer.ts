import {Product} from '../product';
import * as fromRoot from '../../state/app.state';
import {createFeatureSelector, createSelector} from '@ngrx/store';

export interface ProductState {
  showProductCode: boolean;
  currentProduct: Product;
  currentProductId: number;
  products: Product[];
}

export interface AppState extends fromRoot.AppState {
  products: ProductState;
}

const initialState: ProductState = {
  showProductCode: false,
  currentProduct: null,
  currentProductId: 0,
  products: []
};

const featureSelector = createFeatureSelector<ProductState>('products');
export const showProductCodeSelector = createSelector(featureSelector, product => product.showProductCode);
export const productsSelector = createSelector(featureSelector, product => product.products);
export const currentProductIdSelector = createSelector(featureSelector, product => product.currentProductId);
// export const currentProductSelector = createSelector(featureSelector, product => product.currentProduct);
export const currentProductSelector = createSelector(featureSelector, currentProductIdSelector,
  (state, currentProductId) => state.products.find(value => value.id === currentProductId));

export function reducer(state = initialState, action): ProductState {
  switch (action.type) {
    case 'TOGGLE_PRODUCT_CODE':
      console.log('existing state: ' + JSON.stringify(state));
      console.log('action : ' + JSON.stringify(action));

      return {
        ...state,
        showProductCode: action.payload
      };
    default:
      return state;
  }
}
