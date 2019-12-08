import {Injectable} from '@angular/core';
import {Actions, Effect, ofType} from '@ngrx/effects';
import {addUser, addUserFail, addUserSuccess, loadUsers, loadUsersFail, loadUsersSuccess} from './user.actions';
import {catchError, map, mergeMap} from 'rxjs/operators';
import {UserService} from '../user.service';
import {of} from 'rxjs';

@Injectable()
export class UserEffects {

  @Effect()
  loadUser$ = this.action$.pipe(
    ofType(loadUsers),
    mergeMap(() => this.userService.getUsers()
      .pipe(
        map(users => loadUsersSuccess({users})),
        catchError(() => of(loadUsersFail({error: 'Records not found!!!'})))
      )
    )
  );

  @Effect()
  addUser$ = this.action$.pipe(
    ofType(addUser),
    mergeMap(action => this.userService.addUser(action.user)
      .pipe(
        map(user => addUserSuccess({user})),
        catchError(error => of(addUserFail({error: 'Failed to add user!!!'})))
      )
    )
  );

  constructor(private action$: Actions,
              private userService: UserService) {
  }

}
