import {Component, EventEmitter, Input, Output} from '@angular/core';
import {Customer} from '../../../models';

@Component({
  selector: 'app-customers-list',
  templateUrl: './customer-list.component.html',
  styleUrls: ['./customer-list.component.css']
})
export class CustomerListComponent {
  @Input() customers: Customer[];
  @Output() selected = new EventEmitter<Customer>();
  @Output() deleted = new EventEmitter<Customer>();
}
