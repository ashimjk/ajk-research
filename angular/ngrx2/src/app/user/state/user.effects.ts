// import {Injectable} from '@angular/core';
// import {Actions, Effect, ofType} from '@ngrx/effects';
// import {UserActions, UserActionType} from './user.action';
// import {map, mergeMap} from 'rxjs/operators';
// import {UserService} from '../user.service';
//
// @Injectable
// export class UserEffects {
//
//   @Effect()
//   login$ = this.actions$.pipe(
//     ofType(UserActions.LOGIN),
//     mergeMap(
//       action => {
//         this.userService.login({...action}).pipe(
//           map(status => U)
//         );
//       }
//     )
//   );
//
//   constructor(private actions$: Actions,
//               private userService: UserService) {
//   }
//
// }
