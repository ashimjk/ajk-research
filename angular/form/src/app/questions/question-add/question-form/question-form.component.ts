import {Component, Input} from '@angular/core';
import {FormGroup} from '@angular/forms';
import {QuestionBase} from '../../model/question-base';

@Component({
  selector: 'app-question-form',
  templateUrl: './question-form.component.html',
  styleUrls: ['./question-form.component.css']
})
export class QuestionFormComponent {
  @Input() questionForm: FormGroup;
  @Input() question: QuestionBase;

  get isValid() {
    return !this.questionForm.controls[this.question.id].valid
      && this.questionForm.controls[this.question.id].touched;
  }

}
