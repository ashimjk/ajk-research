import {Component, OnInit} from '@angular/core';
import {FormArray, FormControl, FormGroup, Validators} from '@angular/forms';
import {Observable} from 'rxjs';

@Component({
  selector: 'app-reactive-driven',
  templateUrl: './reactive-driven.component.html',
  styleUrls: ['./reactive-driven.component.css']
})
export class ReactiveDrivenComponent implements OnInit {
  defaultGenders = ['male', 'female'];
  forbiddenNames = ['test', 'prod'];

  signupForm: FormGroup;

  ngOnInit() {
    this.signupForm = new FormGroup({
      userData: new FormGroup({
        username: new FormControl(null, [Validators.required, this.validateForbiddenNames.bind(this)]),
        email: new FormControl(null, [Validators.required, Validators.email], this.validateEmail),
      }),
      gender: new FormControl(this.defaultGenders[0]),
      hobbies: new FormArray([])
    });

    this.signupForm.statusChanges.subscribe(value => console.log('SignUp Form Status: ' + value));
    this.signupForm.get('userData.email').valueChanges.subscribe(value => console.log('Email Changes Value: ' + value));
    this.signupForm.get('userData.email').statusChanges.subscribe(value => console.log('Email Changes Status: ' + value));
  }

  onSubmit() {
    console.log(this.signupForm);
    this.signupForm.reset();
  }

  onAddHobby() {
    const hobby = new FormControl(null, Validators.required);
    (this.signupForm.get('hobbies') as FormArray).push(hobby);
  }

  get hobbies(): FormArray {
    return this.signupForm.get('hobbies') as FormArray;
  }

  validateForbiddenNames(control: FormControl): { [key: string]: boolean } {
    if (control.value && this.forbiddenNames.indexOf(control.value) !== -1) {
      return {forbiddenName: true};
    }
    return null;
  }

  isValid(formControlName: string): boolean {
    return !this.signupForm.get(formControlName).valid && this.signupForm.get(formControlName).touched;
  }

  validateEmail(formControl: FormControl): Promise<any> | Observable<any> {
    return new Promise<any>(
      resolve => {
        setTimeout(() => {
          if (formControl.value === 'test@test.com') {
            resolve({emailIsForbidden: true});
          } else {
            resolve(null);
          }
        }, 1500);
      }
    );
  }
}
