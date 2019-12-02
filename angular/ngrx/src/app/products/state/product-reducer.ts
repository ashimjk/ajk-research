import {Product} from '../product';
import * as fromRoot from '../../state/app.state';
import {createFeatureSelector, createSelector} from '@ngrx/store';
import {ProductActions, ProductActionTypes} from './product.actions';

export interface ProductState {
  showProductCode: boolean;
  currentProduct: Product;
  currentProductId: number;
  products: Product[];
  error: string;
}

export interface AppState extends fromRoot.AppState {
  products: ProductState;
}

const initialState: ProductState = {
  showProductCode: false,
  currentProduct: null,
  currentProductId: 0,
  products: [],
  error: ''
};

const featureSelector = createFeatureSelector<ProductState>('products');
export const showProductCodeSelector = createSelector(featureSelector, product => product.showProductCode);
export const productsSelector = createSelector(featureSelector, product => product.products);
export const currentProductSelector = createSelector(featureSelector, product => product.currentProduct);
// export const currentProductSelector = createSelector(featureSelector, currentProductIdSelector,
//   (state, currentProductId) => state.products.find(value => value.id === currentProductId));
export const errorSelector = createSelector(featureSelector, state => state.error);

export function reducer(state = initialState, action: ProductActions): ProductState {
  switch (action.type) {
    case ProductActionTypes.ToggleProductCode:
      return {
        ...state,
        showProductCode: action.payload
      };

    case ProductActionTypes.SetCurrentProduct:
      return {
        ...state,
        currentProduct: {...action.payload}
      };

    case ProductActionTypes.ClearCurrentProduct:
      return {
        ...state,
        currentProduct: null
      };

    case ProductActionTypes.InitializeCurrentProduct:
      return {
        ...state,
        currentProduct: {
          id: 0,
          productName: '',
          productCode: 'New',
          description: '',
          starRating: 0
        }
      };

    case ProductActionTypes.LoadSuccess:
      return {
        ...state,
        products: action.payload
      };

    case ProductActionTypes.LoadFail:
      return {
        ...state,
        error: action.payload
      };

    default:
      return state;
  }
}
