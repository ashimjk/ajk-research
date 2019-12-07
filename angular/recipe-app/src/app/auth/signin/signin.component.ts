import {Component, OnInit} from '@angular/core';
import {NgForm} from '@angular/forms';
import {AppState} from '../../store/app.state';
import {Store} from '@ngrx/store';
import {TrySignIn} from '../store/auth.actions';

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.css']
})
export class SigninComponent implements OnInit {

  constructor(private store: Store<AppState>) {
  }

  ngOnInit() {
  }

  onSignIn(form: NgForm) {
    const username = form.value.email;
    const password = form.value.password;

    this.store.dispatch(new TrySignIn({username, password}));
  }

}
