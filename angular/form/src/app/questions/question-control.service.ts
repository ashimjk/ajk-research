import {Injectable} from '@angular/core';
import {FormControl, FormGroup, ValidatorFn, Validators} from '@angular/forms';
import {QuestionBase} from './model/question-base';

const VALIDATORS: { [key: string]: ValidatorFn } = {
  required: Validators.required,
  email: Validators.email
};

@Injectable({
  providedIn: 'root'
})
export class QuestionControlService {

  toFormGroup(questions: Array<QuestionBase>): FormGroup {
    const group: any = {};

    questions.forEach(question => {
      const validators: ValidatorFn[] = [];

      this.addRequiredValidatorIfTrue(validators, question.required);
      this.addValidatorsIfAvailable(validators, question.validators);

      group[question.id] = new FormControl(question.value || '', [...validators]);
    });

    return new FormGroup(group);
  }

  addRequiredValidatorIfTrue(validators: ValidatorFn[], isRequired: boolean) {
    if (isRequired) {
      validators.push(VALIDATORS.required);
    }
  }

  addValidatorsIfAvailable(validators: ValidatorFn[], validatorName: Array<string>) {

    validatorName.forEach((name: string) => {
      if (VALIDATORS.hasOwnProperty(name)) {
        validators.push(VALIDATORS[name]);
      }
    });
  }
}

