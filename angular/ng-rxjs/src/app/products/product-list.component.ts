import {ChangeDetectionStrategy, Component, OnInit} from '@angular/core';

import {combineLatest, EMPTY, Observable, Subject} from 'rxjs';
import {ProductService} from './product.service';
import {catchError, map} from 'rxjs/operators';
import {ProductCategoryService} from '../product-categories/product-category.service';
import {ProductCategory} from '../product-categories/product-category';

@Component({
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class ProductListComponent implements OnInit {
  pageTitle = 'Product List';
  categories$: Observable<ProductCategory[]>;
  errorMessage$ = new Subject<string>();

  productsWithCategory$ = combineLatest(
    this.productService.productsWithAdded$,
    this.productService.categoryFilterAction$
  )
    .pipe(
      map(
        ([products, categoryId]) => products.filter(product => categoryId ? product.categoryId === categoryId : true)
      ),
      catchError(err => {
        this.errorMessage$.next(err);
        // return of([]);
        return EMPTY;
      })
    );

  constructor(private productService: ProductService,
              private productCategoryService: ProductCategoryService) {
  }

  ngOnInit(): void {
    this.categories$ = this.productCategoryService.categories$;
  }

  onAdd(): void {
    this.productService.addProduct();
  }

  onSelected(categoryId: string): void {
    this.productService.filterByCategoryId(+categoryId);
  }
}
