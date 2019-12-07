import {Component, OnInit} from '@angular/core';
import {NgForm} from '@angular/forms';
import {TrySignUp} from '../store/auth.actions';
import {AppState} from '../../store/app.state';
import {Store} from '@ngrx/store';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  constructor(private store: Store<AppState>) {
  }

  ngOnInit() {
  }

  onSignUp(form: NgForm) {
    const username = form.value.email;
    const password = form.value.password;

    this.store.dispatch(new TrySignUp({username, password}));
  }

}
