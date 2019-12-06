import {Injectable} from '@angular/core';
import * as firebase from 'firebase/app';
import {Router} from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private token: string;

  constructor(private router: Router) {
  }

  signUpUser(email: string, password: string) {
    firebase.auth().createUserWithEmailAndPassword(email, password)
      .catch(
        error => console.error(error)
      );
  }

  signInUser(email: string, password: string) {
    firebase.auth().signInWithEmailAndPassword(email, password)
      .then(response => {
        this.router.navigate(['/']);
        firebase.auth().currentUser.getIdToken()
          .then(tokn => this.token = tokn);
      })
      .catch(error => console.error(error));
  }

  getToken() {
    firebase.auth().currentUser.getIdToken()
      .then(tokn => {
        console.log('token fetched');
        this.token = tokn;
      });

    return this.token;
  }

  logout() {
    firebase.auth().signOut();
    this.token = null;
    this.router.navigate(['/signin']);
  }

  isAuthenticated(): boolean {
    return this.token != null;
  }
}
