import {Injectable} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {QuestionBase} from './model/question-base';

@Injectable({
  providedIn: 'root'
})
export class QuestionControlService {

  toFormGroup(questions: Array<QuestionBase>): FormGroup {
    const group: any = {};

    questions.forEach(question => {
      group[question.key] = question.required
        ? new FormControl(question.value || '', Validators.required)
        : new FormControl(question.value || '');
    });

    return new FormGroup(group);
  }
}

