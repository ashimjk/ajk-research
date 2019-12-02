import {Component, OnDestroy, OnInit} from '@angular/core';

import {Product} from '../product';
import {ProductService} from '../product.service';
import {select, Store} from '@ngrx/store';
import {AppState, currentProductSelector, showProductCodeSelector} from '../state/product-reducer';
import {InitializeCurrentProduct, SetCurrentProduct, ToggleProductCode} from '../state/product.actions';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit, OnDestroy {
  pageTitle = 'Products';
  errorMessage: string;
  displayCode: boolean;
  products: Product[];

  // Used to highlight the selected product in the list
  selectedProduct: Product | null;

  constructor(private store: Store<AppState>,
              private productService: ProductService) {
  }

  ngOnInit(): void {
    // TODO : Unsubscribe
    this.store.pipe(select(currentProductSelector)).subscribe(
      selectedProduct => this.selectedProduct = selectedProduct
    );

    // TODO : Unsubscribe
    this.productService.getProducts().subscribe({
      next: (products: Product[]) => this.products = products,
      error: (err: any) => this.errorMessage = err.error
    });

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
    this.store.dispatch(SetCurrentProduct.create(product));
  }

}
