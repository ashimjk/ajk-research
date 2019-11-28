import {Injectable} from '@angular/core';
import {Subject} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  // tslint:disable-next-line:variable-name
  private _userActivated = new Subject<number>();

  get userActivated() {
    if (this._userActivated.isStopped) {
      this._userActivated = new Subject<number>();
    }

    return this._userActivated;
  }

}
