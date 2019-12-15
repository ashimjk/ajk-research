import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {ContactRequest, PersonalData} from '../model/contact-request';

@Component({
  selector: 'app-beneficiary-add',
  templateUrl: './beneficiary-add.component.html',
  styleUrls: ['./beneficiary-add.component.css']
})
export class BeneficiaryAddComponent implements OnInit {

  contactForm: FormGroup;
  countries = ['USA', 'Germany', 'Italy', 'France'];
  requestTypes = ['Claim', 'Feedback', 'Help Request'];
  submittedValue: string;

  constructor(private fb: FormBuilder) {
  }

  ngOnInit(): void {
    this.contactForm = this.fb.group({
      personalData: this.fb.group(new PersonalData()),
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
    this.contactForm.reset({personalData: new PersonalData(), requestType: '', text: ''});
  }
}
