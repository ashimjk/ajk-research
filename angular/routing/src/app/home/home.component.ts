import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {LoginService} from '../shared/login.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  allowServer = false;

  constructor(private router: Router,
              private loginService: LoginService) {
    setTimeout(() => this.allowServer = true, 2000);
  }

  ngOnInit() {
  }

  onLoadServer1(id: number) {
    this.router.navigate(['/servers', id, 'edit'], {queryParams: {allowEdit: 1}, fragment: 'loading'});
  }

  onLogin() {
    this.loginService.login();
  }

  onLogout() {
    this.loginService.logout();
  }
}
