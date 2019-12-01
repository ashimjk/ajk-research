import {Pipe, PipeTransform} from '@angular/core';
import {Server} from './server';

@Pipe({
  name: 'statusFilter',
  pure: false
})
export class StatusFilterPipe implements PipeTransform {

  transform(servers: Server[], status: string): Server[] {
    if (!status || servers.length === 0) {
      return servers;
    }

    const filterServer: Server[] = [];

    for (const server of servers) {
      if (server.status.startsWith(status)) {
        filterServer.push(server);
      }
    }

    return filterServer;
  }

}
