import {Component, OnDestroy, OnInit} from '@angular/core';

import {Product} from '../product';
import {select, Store} from '@ngrx/store';
import {AppState, currentProductSelector, errorSelector, productsSelector, showProductCodeSelector} from '../state/product-reducer';
import {InitializeCurrentProduct, Load, SetCurrentProduct, ToggleProductCode} from '../state/product.actions';
import {Observable} from 'rxjs';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit, OnDestroy {
  pageTitle = 'Products';
  displayCode: boolean;

  // Used to highlight the selected product in the list
  selectedProduct: Product | null;
  products$: Observable<Product[]>;
  errorMessage$: Observable<string>;

  constructor(private store: Store<AppState>) {
  }

  ngOnInit(): void {
    // TODO : Unsubscribe
    this.store.pipe(select(currentProductSelector)).subscribe(
      selectedProduct => this.selectedProduct = selectedProduct
    );

    // this.productService.getProducts().subscribe({
    //   next: (products: Product[]) => this.products = products,
    //   error: (err: any) => this.errorMessage = err.error
    // });

    this.store.dispatch(Load.create());
    this.products$ = this.store.pipe(select(productsSelector));
    this.errorMessage$ = this.store.pipe(select(errorSelector));

    // TODO : Unsubscribe
    this.store.pipe(select(showProductCodeSelector))
      .subscribe(showProductCode => this.displayCode = showProductCode);
  }

  ngOnDestroy(): void {
  }

  checkChanged(value: boolean): void {
    this.store.dispatch(ToggleProductCode.create(value));
  }

  newProduct(): void {
    this.store.dispatch(InitializeCurrentProduct.create());
  }

  productSelected(product: Product): void {
    this.store.dispatch(SetCurrentProduct.create(product.id));
  }

}
