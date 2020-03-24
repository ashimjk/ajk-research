import {Component} from '@angular/core';
import {BeneficiaryService} from '../services/beneficiary.service';
import {Router} from '@angular/router';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-create-beneficiary',
  templateUrl: 'create-beneficiary.component.html',
  providers: [BeneficiaryService]
})
export class CreateBeneficiaryComponent {

  beneficiaryForm: FormGroup;

  constructor(private valueService: BeneficiaryService, private router: Router, fb: FormBuilder) {
    this.beneficiaryForm = fb.group({
      fullName: ['', [Validators.required, Validators.minLength(2)]],
      nationality: ''
    });
  }

  createValue() {
    const value = this.beneficiaryForm.value;
    if (this.beneficiaryForm.valid) {
      this.valueService.create(value).subscribe(createdValue => {
        console.log('created value: ' + createdValue);
        this.router.navigate(['list']);
      });
    }
  }

}
