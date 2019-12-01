import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-directive-shell',
  template: `
    <p>appInputAttr</p>
    <appInputAttr type="text"></appInputAttr>
  `,
  styles: []
})
export class DirectiveShellComponent implements OnInit {

  constructor() {
  }

  ngOnInit() {
  }

}
