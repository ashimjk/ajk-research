import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

import {BehaviorSubject, combineLatest, merge, Observable, Subject, throwError} from 'rxjs';
import {catchError, delay, map, scan} from 'rxjs/operators';

import {Product} from './product';
import {SupplierService} from '../suppliers/supplier.service';
import {ProductCategoryService} from '../product-categories/product-category.service';
import {isArray} from 'util';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private productsUrl = 'api/products';
  private suppliersUrl = this.supplierService.suppliersUrl;

  private products$: Observable<Product[]>;
  private productsWithCategory$: Observable<Product[]>;

  private categoryFilterSubject = new BehaviorSubject<number>(0);
  categoryFilterAction$ = this.categoryFilterSubject.asObservable();

  private selectedProductSubject = new BehaviorSubject<number>(0);
  private selectedProductAction$ = this.selectedProductSubject.asObservable();
  selectedProduct$: Observable<Product>;

  private addProductSubject = new Subject<Product>();
  private addProductAction$ = this.addProductSubject.asObservable();
  productsWithAdded$: Observable<Product[]>;

  constructor(private http: HttpClient,
              private supplierService: SupplierService,
              private productCategoryService: ProductCategoryService) {

    this.loadProducts$();
    this.loadProductsWithCategory$();
    this.loadSelectedProduct$();
    this.loadProductsWithAdded$();
  }

  private loadProducts$() {
    this.products$ = this.http.get<Product[]>(this.productsUrl)
      .pipe(
        catchError(this.handleError)
      );
  }

  private loadProductsWithCategory$() {
    this.productsWithCategory$ = combineLatest(
      this.products$,
      this.productCategoryService.categories$
    )
      .pipe(
        map(([products, categories]) =>
          products.map(
            product => (({
              ...product,
              price: product.price * 1.5,
              searchKey: [product.productName],
              category: categories.find(category => category.id === product.categoryId).name
            }) as Product)
          )
        )
      );
  }

  private loadSelectedProduct$(): void {
    this.selectedProduct$ = combineLatest(
      this.productsWithCategory$,
      this.selectedProductAction$
    )
      .pipe(
        map(
          ([products, selectedProductId]) =>
            products.find(product => product.id === selectedProductId)
        )
      );
  }

  private loadProductsWithAdded$(): void {
    this.productsWithAdded$ = merge(
      this.productsWithCategory$.pipe(delay(10000)),
      this.addProductAction$
    )
      .pipe(
        scan((acc, value) => [...this.toArray(acc), ...this.toArray(value)]),
        map(value => this.toArray(value))
      );
  }

  filterByCategoryId(categoryId: number): void {
    this.categoryFilterSubject.next(categoryId);
  }

  selectProduct(selectedProductId: number): void {
    this.selectedProductSubject.next(selectedProductId);
  }

  addProduct(product?: Product) {
    product = product || this.fakeProduct();
    this.addProductSubject.next(product);
  }

  private toArray(object: any): Array<any> {
    return isArray(object) ? object : [object];
  }

  private fakeProduct() {
    return {
      id: 42,
      productName: 'Another One',
      productCode: 'TBX-0042',
      description: 'Our new product',
      price: 8.9,
      categoryId: 3,
      category: 'Toolbox',
      quantityInStock: 30
    };
  }

  private handleError(err: any) {
    // in a real world app, we may send the server to some remote logging infrastructure
    // instead of just logging it to the console
    let errorMessage: string;
    if (err.error instanceof ErrorEvent) {
      // A client-side or network error occurred. Handle it accordingly.
      errorMessage = `An error occurred: ${err.error.message}`;
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong,
      errorMessage = `Backend returned code ${err.status}: ${err.body.error}`;
    }
    console.error(err);
    return throwError(errorMessage);
  }

}
