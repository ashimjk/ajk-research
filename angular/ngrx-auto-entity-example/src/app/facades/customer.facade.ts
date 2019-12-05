import {Injectable} from '@angular/core';
import {AppState, CustomerFacadeBase} from '../state';
import {select, Store} from '@ngrx/store';
import {Customer} from '../models';
import {combineLatest, Observable} from 'rxjs';
import {map} from 'rxjs/operators';
import {Modal} from '../services/modal';

@Injectable({providedIn: 'root'})
export class CustomerFacade extends CustomerFacadeBase {
  constructor(private store: Store<AppState>, private modal: Modal) {
    super(Customer, store);
  }

  get first$(): Observable<Customer> {
    // return this.store.pipe(select(firstCustomer));
    return null;
  }

  get currentOrFirst$(): Observable<Customer> {
    return combineLatest(this.current$, this.first$).pipe(
      map(([current, first]) => current || first)
    );
  }

  save(customer: Customer): void {
    customer.id ? this.update(customer) : this.create(customer);
  }
}
