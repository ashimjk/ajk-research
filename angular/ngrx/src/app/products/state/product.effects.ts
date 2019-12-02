import {Injectable} from '@angular/core';
import {Actions, Effect, ofType} from '@ngrx/effects';
import {ProductService} from '../product.service';
import {Load, LoadSuccess, ProductActionTypes} from './product.actions';
import {map, mergeMap} from 'rxjs/operators';
import {Product} from '../product';

@Injectable()
export class ProductEffects {

  constructor(private actions$: Actions<Load>,
              private productService: ProductService) {
  }

  @Effect()
  loadProducts$ = this.actions$.pipe(
    ofType(ProductActionTypes.Load),
    mergeMap(
      (action: Load) => this.productService.getProducts().pipe(
        map((products: Product[]) => LoadSuccess.create(products))
      )
    )
  );


}
