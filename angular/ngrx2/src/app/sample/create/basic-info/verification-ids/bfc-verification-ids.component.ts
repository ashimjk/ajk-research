import {Component, Input} from '@angular/core';
import {Store} from '@ngrx/store';
import {Observable} from 'rxjs';
import {
  addArrayControl,
  createFormArrayState,
  createFormControlState,
  createFormGroupState,
  FormArrayState,
  FormControlState,
  FormGroupState
} from 'ngrx-forms';
import {BeneficiaryCreateForm, ID_TYPE_INITIAL_STATE} from '../../store/beneficiary-create.state';
import {AddFormControl, RemoveFormControl} from '../../store/beneficiary.actions';
import {IdTypeRequest} from '../../../idTypeRequest';

@Component({
  selector: 'app-verification-ids',
  templateUrl: './bfc-verification.ids.component.html'
})
export class BfcVerificationIdsComponent {

  @Input() formState$: Observable<FormGroupState<BeneficiaryCreateForm>>;


  private formControlState: FormControlState<string>;
  private groupState: FormGroupState<IdTypeRequest>;
  private arrayOfFormState: FormArrayState<IdTypeRequest>;
  private arrayOfGroupState: FormArrayState<FormGroupState<IdTypeRequest>>;
  private idTypesFormArrayState: FormArrayState<IdTypesContainer>;

  constructor(public store: Store<any>) {
    this.formControlState = createFormControlState<string>('control ID', '');
    this.groupState = createFormGroupState<IdTypeRequest>('idTypes', ID_TYPE_INITIAL_STATE);
    this.arrayOfFormState = createFormArrayState<IdTypeRequest>('idTypes', [ID_TYPE_INITIAL_STATE]);
    this.idTypesFormArrayState = createFormArrayState<IdTypesContainer>('idTypes', [IdTypesContainer.init]);
    this.arrayOfGroupState = createFormArrayState<FormGroupState<IdTypeRequest>>('idTypes', [this.groupState]);

    // add group state in array of group state
    addArrayControl(this.groupState)(this.arrayOfGroupState);
  }

  onAddIdType() {
    this.store.dispatch(new AddFormControl(ID_TYPE_INITIAL_STATE));
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

  printArrayOfIdTypes() {
    console.log(this.idTypesFormArrayState);
  }

  printArrayOfGroupState() {
    console.log(this.arrayOfGroupState);
  }

  print(value: any) {
    console.log(value);
  }

}

class IdTypesContainer {
  static init: IdTypesContainer = {
    idTypes: [ID_TYPE_INITIAL_STATE]
  };

  idTypes: IdTypeRequest[];
}
