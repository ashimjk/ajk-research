import {Component, Input} from '@angular/core';
import {Store} from '@ngrx/store';
import {Observable} from "rxjs";
import {
  addArrayControl,
  createFormArrayState,
  createFormControlState,
  createFormGroupState,
  FormArrayState,
  FormControlState,
  FormGroupState
} from "ngrx-forms";
import {BeneficiaryCreateForm, ID_TYPE_INITIAL_STATE} from "../../store/beneficiary-create.state";
import {AddFormControl, RemoveFormControl} from "../../store/beneficiary.actions";
import {IdTypeRequest} from "@corpay/beneficiary-client";

@Component({
  selector: 'verification-ids',
  template: `
    <div class="verification-ids">
      <button type="button" class="btn btn-primary" (click)="onAddIdType()">Add</button>

      <br>
      <br>

      <div *ngFor="let control of (formState$ | async).controls.idTypes.controls; let i = index">
        {{ control }} - {{ i }} - {{ control.id }}

        <div class="row">
          <div class="col-xl-4">
            <!--            [formControlState]="control['controls']['name']"-->
            <corpay-input-text label="Id Type"
                               [formControlState]="control.value.name"
                               [required]="true">
            </corpay-input-text>
          </div>
        </div>
      </div>


      <div class="row">
        <div class="col-xl-4">
          <label for="check">Form Control State</label>
          <input type="text" id="check" [ngrxFormControlState]="formControlState">
        </div>
      </div>

      <div class="row">
        <div class="col-xl-4">
          <label for="check">Group Control State</label>
          <input type="text" id="check" [ngrxFormControlState]="groupState.controls.name">

          <div>
<!--            {{ print(groupState) }}-->
          </div>
          <br>
          <button class="btn btn-primary" type="button" (click)="printArrayOfFormState()">Print Group Control State
            State
          </button>
        </div>
      </div>

      <div class="row">
        <div class="col-xl-6">
          <div *ngFor="let control of arrayOfFormState.controls">
            <label for="check">Array of Form Control State</label><br>
            <input type="text" id="check" [ngrxFormControlState]="control['controls']['name']">

            <div>
<!--              {{ print(control['controls']) }}-->
            </div>

            <br>
            <button class="btn btn-primary" type="button" (click)="printArrayOfFormState()">Print Array of Form Control
              State
            </button>
          </div>
        </div>
      </div>

      <div class="row">
        <div class="col-xl-6">
          <div *ngFor="let control of idTypesFormArrayState.controls">
            <label for="check">Array of IdTypes</label><br>
            <input type="text" id="check" [ngrxFormControlState]="control['controls']['name']">

            <div>
              {{ print(control) }}
            </div>

            <br>
            <button class="btn btn-primary" type="button" (click)="printArrayOfFormState()">Print Array of Form Control
              State
            </button>
          </div>
        </div>
      </div>

      <div class="row">
        <div class="col-xl-4">
          <div *ngFor="let control of arrayOfGroupState.controls">
            <label for="check">Array of Group Control State</label>
            <input type="text" id="check" [ngrxFormControlState]="control.value.controls.name">

            <div>
<!--              {{ print(control) }}-->
            </div>

            <button class="btn btn-primary" type="button" (click)="printArrayOfGroupState()">Print Array of Group
              Control
              State
            </button>
          </div>
        </div>
      </div>

    </div>
  `,
  styleUrls: ['./bfc-verification-ids.component.scss']
})
export class BfcVerificationIdsComponent {

  @Input() formState$: Observable<FormGroupState<BeneficiaryCreateForm>>;
  formattedFormState: string;
  private formControlState: FormControlState<string>;
  private groupState: FormGroupState<IdTypeRequest>;
  private arrayOfFormState: FormArrayState<IdTypeRequest>;
  private arrayOfGroupState: FormArrayState<FormGroupState<IdTypeRequest>>;
  private idTypesFormArrayState: FormArrayState<IdTypes>;

  constructor(public store: Store<any>) {
    this.formControlState = createFormControlState<string>('control ID', '');
    this.groupState = createFormGroupState<IdTypeRequest>('idTypes', ID_TYPE_INITIAL_STATE);
    this.arrayOfFormState = createFormArrayState<IdTypeRequest>('idTypes', [ID_TYPE_INITIAL_STATE]);
    this.idTypesFormArrayState = createFormArrayState<IdTypes>('idTypes', [IdTypes.init]);
    this.arrayOfGroupState = createFormArrayState<FormGroupState<IdTypeRequest>>('idTypes', [this.groupState]);

    // add group state in array of group state
    addArrayControl(this.groupState)(this.arrayOfGroupState);
  }

  onAddIdType() {

    this.store.dispatch(new AddFormControl(ID_TYPE_INITIAL_STATE))
  }

  onRemoveIdType(index: number) {
    this.store.dispatch(new RemoveFormControl(index));
  }

  printGroupState() {
    console.log(this.groupState);
  }

  printArrayOfFormState() {
    console.log(this.arrayOfFormState);
  }

  printArrayOfGroupState() {
    console.log(this.arrayOfGroupState);
  }

  print(value: any) {
    console.log(JSON.stringify(value, null, 2));
  }



}

class IdTypes {
  idTypes: IdTypeRequest[];

  static init:IdTypes = {
    idTypes: [ID_TYPE_INITIAL_STATE]
  }
}
