import {ChangeDetectionStrategy, Component, OnInit} from '@angular/core';

import {ProductService} from '../product.service';
import {EMPTY, Observable, Subject} from 'rxjs';
import {Product} from '../product';
import {catchError} from 'rxjs/operators';

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class ProductDetailComponent implements OnInit {
  pageTitle = 'Product Detail';
  productSuppliers;
  selectedProduct$: Observable<Product>;
  errorMessage$ = new Subject<string>();

  constructor(private productService: ProductService) {
  }

  ngOnInit(): void {
    this.selectedProduct$ = this.productService.selectedProduct$
      .pipe(
        catchError(err => {
          this.errorMessage$.next(err);
          return EMPTY;
        })
      );
  }

}
