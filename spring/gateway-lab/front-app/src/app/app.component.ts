import {Component, OnInit} from '@angular/core';
import {Observable} from "rxjs";
import {ApiService, Service} from "./api.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html'
})
export class AppComponent implements OnInit {

  beneficiaryService$: Observable<Service>;
  notificationService$: Observable<Service>;
  subscribedNotification = [];

  constructor(private apiService: ApiService) {
  }

  ngOnInit(): void {
    this.beneficiaryService$ = this.apiService.getBeneficiaryService();
    this.notificationService$ = this.apiService.getNotificationService();
    this.apiService.subscribeNotification()
      .subscribe(data => {
        if (this.subscribedNotification.length > 10) {
          this.subscribedNotification = [];
        }

        this.subscribedNotification.push(data);
      });
  }
}
