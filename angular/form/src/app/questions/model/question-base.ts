export class Options {
  key: string;
  value: string;
}

export class QuestionBase {
  controlType: string;
  id: string;
  value: string;
  type: string;
  labelName: string;
  required: boolean;
  options: Array<Options>;
  validators: Array<string>;
  order: number;

  constructor(questionProperty: {
    controlType?: string,
    id?: string,
    value?: string,
    type?: string,
    labelName?: string,
    required?: boolean,
    options?: Array<Options>,
    validators?: Array<string>,
    order?: number,
  } = {}) {

    this.controlType = questionProperty.controlType || '';
    this.id = questionProperty.id || '';
    this.value = questionProperty.value;
    this.type = questionProperty.type || '';
    this.labelName = questionProperty.labelName || '';
    this.required = !!questionProperty.required;
    this.options = questionProperty.options || [];
    this.validators = questionProperty.validators || [];
    this.order = questionProperty.order || 1;
  }
}
