import {Injectable, NgZone} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable, Subject} from "rxjs";

export interface Service {
  name: string;
}

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  notificationSubscribe = new Subject();

  constructor(private httpClient: HttpClient,
              private zone: NgZone) {
  }

  getBeneficiaryService(): Observable<Service> {
    return this.httpClient.get<Service>("http://localhost:8080/beneficiary/service");
  }

  getNotificationService(): Observable<Service> {
    return this.httpClient.get<Service>("http://localhost:8080/notification/service");
  }

  subscribeNotification(): Observable<any> {
    return new Observable<any>(subscriber => {
      const eventSource = new EventSource("http://localhost:8080/beneficiary/subscribe");

      eventSource.onmessage = event => {
        this.zone.run(() => {
          subscriber.next(event.data);
        })
      };

      eventSource.onerror = error => {
        this.zone.run(() => {
          subscriber.error(error);
        })
      }
    });
  }
}
