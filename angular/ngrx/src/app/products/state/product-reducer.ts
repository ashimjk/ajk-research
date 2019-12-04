import {Product} from '../product';
import * as fromRoot from '../../state/app.state';
import {createFeatureSelector, createSelector} from '@ngrx/store';
import {ProductActions, ProductActionTypes} from './product.actions';

export interface ProductState {
  showProductCode: boolean;
  currentProductId: number;
  products: Product[];
  error: string;
}

export interface AppState extends fromRoot.AppState {
  products: ProductState;
}

const initialState: ProductState = {
  showProductCode: false,
  currentProductId: 0,
  products: [],
  error: ''
};

const productFeatureSelector = createFeatureSelector<ProductState>('products');
export const showProductCodeSelector = createSelector(productFeatureSelector, product => product.showProductCode);
export const productsSelector = createSelector(productFeatureSelector, product => product.products);
export const currentProductIdSelector = createSelector(productFeatureSelector, product => product.currentProductId);
export const currentProductSelector = createSelector(productFeatureSelector, currentProductIdSelector,
  (state, currentProductId) => {
    if (!currentProductId || currentProductId === 0) {
      return {
        id: 0,
        productName: '',
        productCode: 'New',
        description: '',
        starRating: 0
      };
    } else {
      return {...state.products.find(value => value.id === currentProductId)};
    }
  });

export const errorSelector = createSelector(productFeatureSelector, state => state.error);

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
        currentProductId: action.payload
      };

    case ProductActionTypes.ClearCurrentProduct:
      return {
        ...state,
        currentProductId: 0
      };

    case ProductActionTypes.InitializeCurrentProduct:
      return {
        ...state,
        currentProductId: 0
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
