import {ChangeDetectionStrategy, Component, OnInit} from '@angular/core';

import {EMPTY, Observable, Subject} from 'rxjs';
import {ProductService} from '../product.service';
import {catchError} from 'rxjs/operators';
import {Product} from '../product';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list-alt.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class ProductListAltComponent implements OnInit {
  pageTitle = 'Products';
  productsWithCategory$: Observable<Product[]>;
  selectedProduct$: Observable<Product>;
  errorMessage$ = new Subject<string>();

  constructor(private productService: ProductService) {
  }

  ngOnInit(): void {
    this.productsWithCategory$ = this.productService.productsWithAdded$
      .pipe(
        catchError(err => {
          this.errorMessage$.next(err);
          return EMPTY;
        })
      );

    this.selectedProduct$ = this.productService.selectedProduct$;
  }

  onSelected(selectedProductId: number): void {
    this.productService.selectProduct(selectedProductId);
  }
}
