export class Options {
  key: string;
  value: string;
}

export class QuestionBase {
  value: string;
  key: string;
  label: string;
  required: boolean;
  order: number;
  controlType: string;
  type: string;
  options: Options[];

  constructor(questionProperty: {
    value?: string,
    key?: string,
    label?: string,
    required?: boolean,
    order?: number,
    controlType?: string,
    type?: string,
    options?: Options[]
  } = {}) {

    this.value = questionProperty.value;
    this.key = questionProperty.key || '';
    this.label = questionProperty.label || '';
    this.required = !!questionProperty.required;
    this.order = questionProperty.order || 1;
    this.controlType = questionProperty.controlType || '';
    this.type = questionProperty.type || '';
    this.options = questionProperty.options || [];
  }
}
