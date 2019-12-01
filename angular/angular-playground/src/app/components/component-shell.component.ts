import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-component-shell',
  template: `
    <app-view-provider></app-view-provider>
  `,
  styles: []
})
export class ComponentShellComponent implements OnInit {

  constructor() {
  }

  ngOnInit() {
  }

}
