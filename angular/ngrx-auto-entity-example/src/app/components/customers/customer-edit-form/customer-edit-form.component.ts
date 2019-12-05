import {Component, EventEmitter, Input, OnChanges, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Customer} from '../../../models';

@Component({
  selector: 'app-customers-edit-form',
  templateUrl: './customer-edit-form.component.html',
  styleUrls: ['./customer-edit-form.component.css']
})
export class CustomerEditFormComponent implements OnChanges, OnInit {
  @Input() customer: Customer;
  @Output() submitted = new EventEmitter<Customer>();
  @Output() validated = new EventEmitter<boolean>();

  form: FormGroup;

  constructor(private builder: FormBuilder) {
  }

  ngOnInit(): void {
    this.form = this.buildForm(this.builder);
    this.form.statusChanges.subscribe(() => this.validated.emit(this.form.valid));
  }

  ngOnChanges(): void {
    if (this.customer) {
      this.form.patchValue(this.customer);
    }
  }

  buildForm(builder: FormBuilder): FormGroup {
    return builder.group({
      name: [null, [Validators.required, Validators.maxLength(30)]],
      title: [null, [Validators.required, Validators.maxLength(60)]],
      email: [null, [Validators.required, Validators.maxLength(35)]],
      handles: builder.group({
        twitter: [null, Validators.maxLength(50)],
        facebook: [null, Validators.maxLength(50)]
      }),
      address: builder.group({
        city: [null, Validators.maxLength(50)],
        state: [null, Validators.maxLength(2)],
        zip: [null, [Validators.minLength(5), Validators.maxLength(10)]]
      })
    });
  }

  submit() {
    if (this.form.valid) {
      this.submitted.emit({
        ...this.customer,
        ...this.form.value
      });
    }
  }
}
