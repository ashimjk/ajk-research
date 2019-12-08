import {Injectable} from '@angular/core';
import {Observable, of, throwError} from 'rxjs';
import {User} from './store/user.state';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  getUsers(): Observable<User[]> {
    return of([
      {
        username: 'ashim',
        email: 'ashim.khadka@clusus.com'
      },
      {
        username: 'shekar',
        email: 'shekar.rai@clusus.com'
      },
      {
        username: 'suraj',
        email: 'suraj.gautam@clusus.com'
      }
    ]);

    // return throwError('error');
  }

  addUser(user: User): Observable<User> {
    // return of(user);
    return throwError('error');
  }
}
