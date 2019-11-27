import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Params} from '@angular/router';
import {UserService} from '../shared/user.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
  userId: number;

  constructor(private route: ActivatedRoute,
              private userService: UserService) {
  }

  ngOnInit() {
    this.route.params.subscribe(
      (params: Params) => this.userId = +params.id
    );
  }

  onActivate() {
    this.userService.userActivated.next(this.userId);
  }

  onDeactivate() {
    this.userService.userActivated.complete();
  }
}
