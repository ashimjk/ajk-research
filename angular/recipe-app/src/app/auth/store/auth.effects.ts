import {Injectable} from '@angular/core';
import {Actions, Effect, ofType} from '@ngrx/effects';
import {AppState} from '../../store/app.state';
import {Store} from '@ngrx/store';
import {LOGOUT, SET_TOKEN, SIGNIN, SIGNUP, TRY_SIGNIN, TRY_SIGNUP, TrySignIn, TrySignUp} from './auth.actions';
import {map, mergeMap, switchMap, tap} from 'rxjs/operators';
import {fromPromise} from 'rxjs/internal-compatibility';
import * as firebase from 'firebase';
import {Router} from '@angular/router';

@Injectable()
export class AuthEffects {

  constructor(private store: Store<AppState>,
              private actions$: Actions,
              private router: Router) {
  }

  @Effect()
  authSignUp$ = this.actions$
    .pipe(
      ofType(TRY_SIGNUP),
      map((action: TrySignUp) => action.payload),
      switchMap((authData: { username: string, password: string }) =>
        fromPromise(firebase.auth().createUserWithEmailAndPassword(authData.username, authData.password))
      ),
      switchMap(() => fromPromise(firebase.auth().currentUser.getIdToken())),
      mergeMap((token: string) => {
        this.router.navigate(['/']);
        return [
          {
            type: SIGNUP
          },
          {
            type: SET_TOKEN,
            payload: token
          }
        ];
      })
    );

  @Effect()
  authSignIn$ = this.actions$
    .pipe(
      ofType(TRY_SIGNIN),
      map((action: TrySignIn) => action.payload),
      switchMap((authData: { username: string, password: string }) =>
        fromPromise(firebase.auth().signInWithEmailAndPassword(authData.username, authData.password))
      ),
      switchMap(() => fromPromise(firebase.auth().currentUser.getIdToken())),
      mergeMap((token: string) => {
        this.router.navigate(['/']);
        return [
          {
            type: SIGNIN
          },
          {
            type: SET_TOKEN,
            payload: token
          }
        ];
      })
    );

  @Effect({dispatch: false})
  authLogout$ = this.actions$.pipe(
    ofType(LOGOUT),
    tap(() => {
      firebase.auth().signOut();
      this.router.navigate(['/']);
    })
  );
}
