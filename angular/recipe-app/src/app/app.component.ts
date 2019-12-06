import {Component, OnInit} from '@angular/core';
import * as firebase from 'firebase';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  ngOnInit(): void {
    firebase.initializeApp({
      apiKey: 'AIzaSyBpP2h1Sk4R8VdKX5k5iujNK-ua90H1OPw',
      authDomain: 'recipe-book-15e0f.firebaseio.com',
    });
  }

}
