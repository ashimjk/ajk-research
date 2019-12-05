import {Component, ViewChild} from '@angular/core';
import {CustomerEditFormComponent} from '../customer-edit-form/customer-edit-form.component';
import {CustomerFacade} from '../../../facades';

@Component({
  selector: 'app-customer-edit',
  templateUrl: './customer-edit.component.html',
  styleUrls: ['./customer-edit.component.css']
})
export class CustomerEditComponent {
  @ViewChild('form', {static: false}) form: CustomerEditFormComponent;

  canSave = false;

  constructor(public customers: CustomerFacade) {
  }
}
