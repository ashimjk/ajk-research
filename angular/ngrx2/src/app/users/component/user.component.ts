import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {Observable} from 'rxjs';
import {User, UserState} from '../store/user.state';
import {Store} from '@ngrx/store';
import {AppState} from '../../store/app.state';
import {addUser, loadUsers} from '../store/user.actions';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  userForm: FormGroup;

  userState$: Observable<UserState>;
  users$: Observable<User[]>;
  error$: Observable<string>;

  constructor(private store: Store<AppState>) {
  }

  ngOnInit() {
    this.store.dispatch(loadUsers());

    // this.userState$ = this.store.select(state => state.userState);
    this.userState$ = this.store.select('userState');
    this.error$ = this.store.select(state => state.userState.error);

    // this.users$ = this.store.select(state => state.userState.users);
    this.users$ = this.store.select(state => {

      console.log('App State: ', state);

      console.log('User State(1): ', state.userState);
      console.log('User State(2): ', state.userState);

      console.log('Users: ', state.userState.users);
      console.log('Users: ', state.userState.users);

      return state.userState.users;
    });

    this.userForm = new FormGroup({
      username: new FormControl(),
      email: new FormControl()
    });
  }

  onSubmit() {
    console.log(this.userForm.value);

    this.store.dispatch(addUser({user: this.userForm.value}));
  }
}
