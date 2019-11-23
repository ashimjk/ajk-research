import {EventEmitter, Injectable} from '@angular/core';
import {LoggingService} from './logging.service';

@Injectable({
  providedIn: 'root'
})
export class AccountService {
  accounts = [
    {
      name: 'Master Account',
      status: 'active'
    },
    {
      name: 'Testaccount',
      status: 'inactive'
    },
    {
      name: 'Hidden Account',
      status: 'unknown'
    }
  ];

  statusUpdate = new EventEmitter<string>();

  constructor(private loggingService: LoggingService) {
  }

  addAccount(newAccount: { name: string, status: string }) {
    this.accounts.push(newAccount);
    this.loggingService.logStatus(newAccount.status);
  }

  updateStatus(id: number, newStatus: string) {
    this.accounts[id].status = newStatus;
    this.loggingService.logStatus(newStatus);
  }

}
