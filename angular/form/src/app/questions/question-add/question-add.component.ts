import {Component, OnInit} from '@angular/core';
import {FormGroup} from '@angular/forms';
import {QuestionControlService} from '../question-control.service';
import {QuestionBase} from '../model/question-base';
import {QuestionService} from '../question.service';

@Component({
  selector: 'app-question-add',
  templateUrl: './question-add.component.html',
  styleUrls: ['./question-add.component.css']
})
export class QuestionAddComponent implements OnInit {
  questionForm: FormGroup;
  questions: Array<QuestionBase>;

  payload = '';

  constructor(private questionService: QuestionService,
              private qcs: QuestionControlService) {
  }

  ngOnInit() {
    this.questions = this.questionService.getQuestions();
    this.questionForm = this.qcs.toFormGroup(this.questions);
  }

  onSubmit() {
    this.payload = JSON.stringify(this.questionForm.value);
  }

}
