import {Component, OnInit} from '@angular/core';
import {Observable, Subscriber} from 'rxjs';
import {UserService} from '../shared/user.service';
import {filter, map, scan} from 'rxjs/operators';


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  user1Activated = false;
  user2Activated = false;

  constructor(private userService: UserService) {
  }

  ngOnInit() {
    const myObservable = new Observable(
      (subscriber: Subscriber<string>) => {
        setTimeout(() => subscriber.next('first stage'), 2000);
        setTimeout(() => subscriber.next('second stage'), 4000);
        setTimeout(() => subscriber.complete(), 5000);
        setTimeout(() => subscriber.next('this does not work'), 5000);
      }
    );
    myObservable.subscribe(
      (data: string) => console.log(data),
      (msg: string) => console.log(msg),
      () => console.log('completed')
    );

    this.userService.userActivated
      .pipe(
        filter(x => x % 2 === 0),
        map(x => x + x),
        scan((acc, x) => acc + x)
      )
      .subscribe(x => console.log('value: ' + x));

    this.userService.userActivated.subscribe(
      (id: number) => {
        if (id === 1) {
          this.user1Activated = !this.user1Activated;
        } else if (id === 2) {
          this.user2Activated = !this.user2Activated;
        }
      },
      () => {
      },
      () => {
        this.user1Activated = false;
        this.user2Activated = false;
      }
    );
  }

}
