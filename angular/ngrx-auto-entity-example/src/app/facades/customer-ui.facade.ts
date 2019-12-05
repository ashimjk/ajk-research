import { Injectable } from '@angular/core';

import { Customer } from '../models';
import {Modal} from '../services/modal';
import {CustomerFacade} from './customer.facade';
import {CustomerEditComponent} from '../components/customers/customer-edit/customer-edit.component';

@Injectable({ providedIn: 'root' })
export class CustomerUIFacade {
  constructor(private modal: Modal, private customerFacade: CustomerFacade) {
  }

  edit(customer: Customer): void {
    const reference = this.modal.show(CustomerEditComponent);
    reference.dismissed(editedCustomer => {
      this.customerFacade.save(editedCustomer);
    });
  }
}
