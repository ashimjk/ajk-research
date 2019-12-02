import {Component, OnDestroy, OnInit} from '@angular/core';
import {NgForm} from '@angular/forms';
import {Router} from '@angular/router';

import {AuthService} from './auth.service';
import {select, Store} from '@ngrx/store';
import {Subscription} from 'rxjs';
import {maskUserNameSelector} from './state/user-reducer';
import {AppState} from '../state/app.state';

@Component({
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit, OnDestroy {
  pageTitle = 'Log In';
  errorMessage: string;

  maskUserName: boolean;
  storeSubscription: Subscription;

  constructor(private authService: AuthService,
              private router: Router,
              private store: Store<AppState>) {
  }

  ngOnInit(): void {
    // this.storeSubscription = this.store.pipe(select('users')).subscribe(
    //   users => this.maskUserName = users.maskUserName
    // );

    this.storeSubscription = this.store.pipe(select(maskUserNameSelector))
      .subscribe(maskUserName => this.maskUserName = maskUserName);
  }

  cancel(): void {
    this.router.navigate(['welcome']);
  }

  checkChanged(value: boolean): void {
    // this.maskUserName = value;

    this.store.dispatch({
      type: 'MASK_USER_NAME',
      payload: value
    });
  }

  login(loginForm: NgForm): void {
    if (loginForm && loginForm.valid) {
      const userName = loginForm.form.value.userName;
      const password = loginForm.form.value.password;
      this.authService.login(userName, password);

      if (this.authService.redirectUrl) {
        this.router.navigateByUrl(this.authService.redirectUrl);
      } else {
        this.router.navigate(['/products']);
      }
    } else {
      this.errorMessage = 'Please enter a user name and password.';
    }
  }

  ngOnDestroy(): void {
    this.storeSubscription.unsubscribe();
  }
}
