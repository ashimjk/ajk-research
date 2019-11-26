import { Component } from '@angular/core';

const num = 0;

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'playground';

  sayhello() {
    const x = 10;
  }
}
