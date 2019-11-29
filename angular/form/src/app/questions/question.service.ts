import {Injectable} from '@angular/core';
import {QuestionBase} from './model/question-base';
import {DropdownQuestion} from './model/dropdown-question';
import {TextboxQuestion} from './model/text-question';

@Injectable({
  providedIn: 'root'
})
export class QuestionService {

  getQuestions(): Array<QuestionBase> {

    const questions: Array<QuestionBase> = [

      new DropdownQuestion({
        id: 'brave',
        labelName: 'Bravery Rating',
        value: 'solid',
        options: [
          {key: 'solid', value: 'Solid'},
          {key: 'great', value: 'Great'},
          {key: 'good', value: 'Good'},
          {key: 'unproven', value: 'Unproven'}
        ],
        order: 3
      }),

      new TextboxQuestion({
        id: 'firstName',
        labelName: 'First name',
        value: 'Bombasto',
        required: true,
        order: 1,
        type: 'textbox'
      }),

      new TextboxQuestion({
        id: 'emailAddress',
        labelName: 'Email',
        type: 'email',
        required: true,
        validators: ['email'],
        order: 2
      })
    ];

    return questions.sort((a, b) => a.order - b.order);
  }
}
