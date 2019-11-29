import {QuestionBase} from './question-base';

export class DropdownQuestion extends QuestionBase {
  controlType = 'dropdown';

  constructor(options: any = {}) {
    super(options);
  }
}
