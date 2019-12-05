import {Component, EventEmitter, Input, Output} from '@angular/core';
import {Customer} from '../../../models';

@Component({
  selector: 'app-customer-detail',
  templateUrl: './customer-detail.component.html',
  styleUrls: ['./customer-detail.component.css']
})
export class CustomerDetailComponent {
  @Input() customer: Customer;
  @Output() saved = new EventEmitter<Customer>();
  @Output() deleted = new EventEmitter<Customer>();
  @Output() edited = new EventEmitter<Customer>();
}
