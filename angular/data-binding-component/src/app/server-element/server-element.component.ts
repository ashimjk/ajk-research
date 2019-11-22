import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-server-element',
  templateUrl: './server-element.component.html',
  styles: []
})
export class ServerElementComponent implements OnInit {
  @Input('srvElement') element: { type: string, name: string, content: string };

  constructor() {
  }

  ngOnInit() {
  }

}
