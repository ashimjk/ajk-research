import {createAction, props} from '@ngrx/store';

export const increment = createAction('[Counter Component] Increment');
export const decrement = createAction('[Counter Component] Decrement');
export const reset = createAction('[Counter Component] Reset');
export const setValue = createAction('[Counter Component] Set Value', props<Payload>());

export class Payload {
  value: number;

  constructor(value: number) {
    this.value = value;
  }
}
