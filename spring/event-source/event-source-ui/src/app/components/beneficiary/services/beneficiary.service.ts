import {Injectable} from '@angular/core';
import {Observable, Subject} from 'rxjs';
import {HttpClient, HttpHeaders} from '@angular/common/http';

export class Beneficiary {
  id: string;
  fullName: string;
  nationality: string;
}

@Injectable()
export class BeneficiaryService {

  constructor(private http: HttpClient) {
  }

  getAll(): Observable<Beneficiary[]> {
    return this.http.get<Beneficiary[]>('/beneficiary');
  }

  listBeneficiaryStream(): Subject<any> {
    const eventSource = new EventSource('/beneficiary/subscribe');
    const subscription = new Subject();
    eventSource.addEventListener('message', event => {
      console.log('Got event: ' + event);
      subscription.next(event);
    });

    // setTimeout(() => {
    //   eventSource.close();
    //   console.log('closed');
    // }, 10000);
    return subscription;
  }

  create(beneficiary: Beneficiary): Observable<Beneficiary> {
    const bodyString = JSON.stringify(beneficiary);
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };

    console.log('Creating Beneficiary : ' + bodyString);
    return this.http.post<Beneficiary>('/beneficiary', bodyString, httpOptions);
  }

  update(beneficiary: Beneficiary): Observable<Beneficiary> {
    const bodyString = JSON.stringify(beneficiary);
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };

    console.log('Updating Beneficiary : ' + bodyString);
    return this.http.put<Beneficiary>('/beneficiary/' + beneficiary.id, bodyString, httpOptions);
  }

  get(id: number): Observable<Beneficiary> {
    return this.http.get<Beneficiary>(`/beneficiary/${id}`);
  }
}
