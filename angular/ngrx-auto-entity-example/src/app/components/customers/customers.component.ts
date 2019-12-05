import {Component} from '@angular/core';
import {CustomerFacade, CustomerUIFacade} from '../../facades';

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.css']
})
export class CustomersComponent {
  constructor(public customers: CustomerFacade, public ui: CustomerUIFacade) {
    customers.loadAll();
  }
}
