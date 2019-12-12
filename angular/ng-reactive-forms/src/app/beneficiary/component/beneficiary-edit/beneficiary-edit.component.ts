import {Component} from '@angular/core';
import {FormBuilder, FormControl, FormGroup} from '@angular/forms';
import {ContactRequest, PersonalData} from '../model/contact-request';

@Component({
  selector: 'app-beneficiary-edit',
  templateUrl: './beneficiary-edit.component.html',
  styleUrls: ['./beneficiary-edit.component.css']
})
export class BeneficiaryEditComponent {

  contactForm: FormGroup;

  countries = ['USA', 'Germany', 'Italy', 'France'];

  requestTypes = ['Claim', 'Feedback', 'Help Request'];

  submittedValue: string;


  constructor(private fb: FormBuilder) {
    // this.contactForm = this.createFormGroup();
    this.contactForm = this.createFormGroupWithBuilder(fb);
  }

  createFormGroup() {
    return new FormGroup({
      personalData: new FormGroup({
        email: new FormControl(),
        mobile: new FormControl(),
        country: new FormControl(),
      }),
      requestType: new FormControl(),
      text: new FormControl(),
    });
  }

  createFormGroupWithBuilder(formBuilder: FormBuilder) {
    // return formBuilder.group({
    //   personalData: formBuilder.group({
    //     email: 'defaul@email.com',
    //     mobile: '',
    //     country: ''
    //   }),
    //   requestType: '',
    //   text: ''
    // });

    return formBuilder.group({
      personalData: formBuilder.group(new PersonalData()),
      requestType: '',
      text: ''
    });
  }

  onSubmit() {
    this.submittedValue = JSON.stringify(this.contactForm.value, null, 2);

    const result: ContactRequest = Object.assign({}, this.contactForm.value);
    result.personalData = Object.assign({}, result.personalData);
    console.log(result);
  }

  revert() {
    this.contactForm.reset();

    // Resets to provided model
    this.contactForm.reset({personalData: new PersonalData(), requestType: '', text: ''});
  }
}
