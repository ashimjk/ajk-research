import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {Observable} from 'rxjs';
import {User, UserState} from './store/user.state';
import {Store} from '@ngrx/store';
import {AppState} from '../store/app.state';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  userForm: FormGroup;

  userState$: Observable<UserState>;
  users$: Observable<User[]>;

  constructor(private store: Store<AppState>) {
  }

  ngOnInit() {
    // this.userState$ = this.store.select(state => state.userState);
    this.userState$ = this.store.select('userState');

    // this.users$ = this.store.select(state => state.userState.users);
    this.users$ = this.store.select(state => {

      console.log('App State: ', state);

      console.log('User State(1): ', state.userState);
      console.log('User State(2): ', state['userState']);

      console.log('Users: ', state.userState.users);
      console.log('Users: ', state['userState'].users);

      return state['userState'].users;
    });

    this.userForm = new FormGroup({
      username: new FormControl(),
      email: new FormControl()
    });
  }

  onSubmit() {
    console.log(this.userForm.value);
  }
}
