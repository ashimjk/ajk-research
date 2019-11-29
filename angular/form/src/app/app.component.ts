import {Component, ViewChild} from '@angular/core';
import {NgForm} from '@angular/forms';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  @ViewChild('form', {static: false}) ngForm: NgForm;
  defaultGenders = ['male', 'female'];

  defaultQuestion = 'pet';
  defaultGender = this.defaultGenders[0];

  username: string;
  mail: string;
  question: string;
  answer: string;
  gender: string;

  isSubmitted = false;

  onSubmit() {
    this.isSubmitted = true;
    this.username = this.ngForm.form.value.userData.username;
    this.mail = this.ngForm.form.value.userData.email;
    this.question = this.ngForm.form.value.secret;
    this.answer = this.ngForm.form.value.answer;
    this.gender = this.ngForm.form.value.gender;

    // this.ngForm.reset();
    this.ngForm.reset({
        userData: {
          username: '',
          email: ''
        },
        gender: this.defaultGenders[0],
        secret: this.defaultQuestion,
        answer: ''
      });
  }

  onSuggestUsername() {
    // this.ngForm.setValue({
    //   userData: {
    //     username: 'Super User',
    //     email: ''
    //   },
    //   gender: '',
    //   secret: '',
    //   answer: ''
    // });

    this.ngForm.form.patchValue({
      userData: {
        username: 'Super User'
      }
    });
  }
}
