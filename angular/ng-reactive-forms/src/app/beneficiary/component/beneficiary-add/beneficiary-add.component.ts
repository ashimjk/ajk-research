import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';

@Component({
  selector: 'app-beneficiary-add',
  templateUrl: './beneficiary-add.component.html',
  styleUrls: ['./beneficiary-add.component.css']
})
export class BeneficiaryAddComponent implements OnInit {

  beneficiaryForm: FormGroup;

  constructor(private fb: FormBuilder) {
  }

  ngOnInit(): void {
    this.beneficiaryForm = this.fb.group({
      name: '',
      address: this.fb.group({})
      // address: this.fb.group({
      //   city: new FormControl('', Validators.required),
      //   country: new FormControl('', Validators.required)
      // })
    });
  }

  onSubmit() {
    console.log(this.beneficiaryForm.value);
  }

  private addFormControl(name: string, formGroup: FormGroup): void {
    this.beneficiaryForm.addControl(name, formGroup);
  }
}
