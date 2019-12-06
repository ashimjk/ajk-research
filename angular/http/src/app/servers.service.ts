import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Server} from './server';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ServersService {

  constructor(private http: HttpClient) {
  }

  storeServers(servers: Server[]) {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.put('https://ng-http-61412.firebaseio.com/data.json', servers, {headers});
  }

  getServers(): Observable<Server[]> {
    return this.http.get<Server[]>('https://ng-http-61412.firebaseio.com/data.json');
  }

  getServers2() {
    return this.http.get<Server[]>('https://ng-http-61412.firebaseio.com/data.json')
      .pipe(
        map((servers: Server[]) => {
          servers.forEach(server => server.serverName = 'FETCHED_' + server.serverName);
          return servers;
        })
      );
  }

  getAppName(): Observable<string> {
    return this.http.get<string>('https://ng-http-61412.firebaseio.com/appName.json');
  }
}
