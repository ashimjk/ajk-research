import {Component, Input} from '@angular/core';
import {createFormControlState, FormGroupState, markAsSubmitted, setValue} from 'ngrx-forms';
import {MyFormValue} from './state/my.reducer';

@Component({
  selector: 'app-my-component',
  templateUrl: './my.component.html',
  styleUrls: ['./my.component.css']
})
export class MyComponent {
  @Input() formState: FormGroupState<MyFormValue>;

  changeValue(stringValue: HTMLInputElement) {
    console.log(stringValue.value);

    setValue(this.formState.controls.stringValue, 'ashim');
    markAsSubmitted(this.formState.controls.stringValue);

    const control = createFormControlState<string>('control ID', '');
    const updatedControl = setValue('new Value')(control);
  }
}
