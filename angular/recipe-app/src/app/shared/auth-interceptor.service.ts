import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpParams, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs';
import {AppState} from '../store/app.state';
import {Store} from '@ngrx/store';
import {switchMap, take} from 'rxjs/operators';
import {AuthState} from '../auth/store/auth.state';

@Injectable()
export class AuthInterceptorService implements HttpInterceptor {

  constructor(private store: Store<AppState>) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    console.log('Auth Interceptor : ');

    return this.store.select('auth')
      .pipe(
        take(1),
        switchMap((authState: AuthState) => {
          const clonedRequest = req.clone({params: new HttpParams().append('auth', authState.token)});
          return next.handle(clonedRequest);
        })
      );
  }
}
