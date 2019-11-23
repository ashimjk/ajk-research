import {Component, OnInit} from '@angular/core';
import {AccountService} from '../shared/account.service';

@Component({
  selector: 'app-new-account',
  templateUrl: './new-account.component.html',
  styleUrls: ['./new-account.component.css']
})
export class NewAccountComponent implements OnInit {

  constructor(private accountService: AccountService) {
  }

  ngOnInit(): void {
    this.accountService.statusUpdate.subscribe(
      (status: string) => console.log(status + ' from NewAccountComponent!')
    );
  }

  onCreateAccount(accountName: string, accountStatus: string) {
    this.accountService.addAccount({
      name: accountName,
      status: accountStatus
    });
  }
}