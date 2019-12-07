import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, CanLoad, Route, Router, RouterStateSnapshot, UrlSegment, UrlTree} from '@angular/router';
import {Observable} from 'rxjs';
import {AppState} from '../store/app.state';
import {Store} from '@ngrx/store';
import {map, take} from 'rxjs/operators';
import {AuthState} from './store/auth.state';

@Injectable({
  providedIn: 'root'
})
export class AuthGuardService implements CanActivate, CanLoad {

  constructor(private router: Router,
              private store: Store<AppState>) {
  }

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {

    return this.isAuthenticated;
  }

  canLoad(route: Route, segments: UrlSegment[]): Observable<boolean> | Promise<boolean> | boolean {
    return this.isAuthenticated;
  }

  get isAuthenticated(): Observable<boolean> {
    // return this.store.select('auth').pipe(take(1), map((authState: AuthState) => authState.authenticated));
    return this.store.select('auth').pipe(
      take(1),
      map((authState: AuthState) => {
        if (authState.authenticated) {
          return true;
        } else {
          this.router.navigate(['/signin']);
          return false;
        }
      })
    );
  }

}
