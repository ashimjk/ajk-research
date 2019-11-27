import {Component, OnInit} from '@angular/core';

import {ServersService} from '../servers.service';
import {ActivatedRoute, Params, Router} from '@angular/router';
import {CanComponentDeactivate} from '../../shared/can-deactivate-guard';
import {Observable} from 'rxjs';

@Component({
  selector: 'app-edit-server',
  templateUrl: './edit-server.component.html',
  styleUrls: ['./edit-server.component.css']
})
export class EditServerComponent implements OnInit, CanComponentDeactivate {
  server: { id: number, name: string, status: string };
  serverName = '';
  serverStatus = '';
  allowEdit: boolean;
  changesSaved: boolean;

  constructor(private serversService: ServersService,
              private activatedRoute: ActivatedRoute,
              private router: Router) {
  }

  ngOnInit() {
    this.server = this.serversService.getServer(+this.activatedRoute.snapshot.params.id);
    this.allowEdit = this.activatedRoute.snapshot.queryParams.allowEdit === '1';
    this.serverName = this.server.name;
    this.serverStatus = this.server.status;

    this.activatedRoute.params.subscribe(
      (params: Params) => {
        this.server = this.serversService.getServer(+params.id);
        this.serverName = this.server.name;
        this.serverStatus = this.server.status;
      }
    );

    this.activatedRoute.queryParams.subscribe(
      (queryParams: Params) => this.allowEdit = queryParams.allowEdit === '1'
    );
  }

  onUpdateServer() {
    this.serversService.updateServer(this.server.id, {name: this.serverName, status: this.serverStatus});
    this.changesSaved = true;
    this.router.navigate(['servers', this.server.id]);
  }

  canDeactivate(): Observable<boolean> | Promise<boolean> | boolean {
    if (!this.allowEdit) {
      return true;
    }
    if ((this.serverName !== this.server.name || this.serverStatus !== this.server.status) && !this.changesSaved) {
      return confirm('Do you want to discard the changes?');
    } else {
      return true;
    }
  }

}
