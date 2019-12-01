import {Component} from '@angular/core';
import {Server} from './server';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  servers = [
    {
      instanceType: 'medium',
      name: 'Production',
      status: 'stable',
      started: new Date(2020, 2, 21)
    },
    {
      instanceType: 'large',
      name: 'User Database',
      status: 'critical',
      started: new Date(2014, 5, 23)
    },
    {
      instanceType: 'small',
      name: 'Development Server',
      status: 'offline',
      started: new Date(2012, 1, 17)
    },
    {
      instanceType: 'small',
      name: 'Testing Environment Server',
      status: 'stable',
      started: new Date(2019, 12, 2)
    }
  ];

  statusFilter: string;
  appStatus = new Promise(
    resolve => {
      setTimeout(() => resolve('stable'), 2000);
    }
  );

  getStatusClasses(server: Server) {
    // const response = {
    //   'list-group-item-success': server.status === 'stable',
    //   'list-group-item-warning': server.status === 'offline',
    //   'list-group-item-danger': server.status === 'critical'
    // };

    let response = '';
    if ('stable' === server.status) {
      response = 'list-group-item-success';
    } else if ('offline' === server.status) {
      response = 'list-group-item-warning';
    } else if ('critical' === server.status) {
      response = 'list-group-item-danger';
    }

    console.log(response);
    return response;
  }

  onAddServer() {
    this.servers.push({
      instanceType: 'online',
      name: 'New Server',
      status: 'critical',
      started: new Date(2019, 12, 2)
    });
  }
}
