import {Component} from '@angular/core';
import {ServersService} from './servers.service';
import {Server} from './server';
import {Observable} from 'rxjs';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  appName$: Observable<string>;

  constructor(private serversService: ServersService) {
    this.appName$ = serversService.getAppName();
  }

  servers: Server[] = [
    {
      id: AppComponent.generatedId(),
      serverName: 'Test Server',
      capacity: 10
    },
    {
      id: AppComponent.generatedId(),
      serverName: 'Live Server',
      capacity: 100
    }
  ];

  private static generatedId() {
    return Math.round(Math.random() * 10000);
  }

  onAddServer(name: string) {
    this.servers.push({
      serverName: name,
      capacity: 50,
      id: AppComponent.generatedId()
    });
  }

  onSaveServers() {
    this.serversService.storeServers(this.servers).subscribe(
      response => console.log(response),
      error => console.log(error)
    );
  }

  onGetServers() {
    // this.serversService.getServers().subscribe(
    //   (response: Server[]) => console.log(response),
    //   error => console.log(error)
    // );

    this.serversService.getServers2().subscribe(
      (servers) => this.servers = servers,
      error => console.log(error)
    );
  }
}
