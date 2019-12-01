export class Server {
  instanceType: string;
  name: string;
  status: string;
  started: Date;

  constructor(instanceType: string, name: string, status: string, started: Date) {
    this.instanceType = instanceType;
    this.name = name;
    this.status = status;
    this.started = started;
  }

}
