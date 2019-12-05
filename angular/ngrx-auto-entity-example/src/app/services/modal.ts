import {Injectable} from '@angular/core';

@Injectable()
export class Modal {

  show(CustomerEditComponent: any): Popup {
    return null;
  }
}

export class Popup {

  dismissed(param: (editedCustomer) => void) {
  }
}
