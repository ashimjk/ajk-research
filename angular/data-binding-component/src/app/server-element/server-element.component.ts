import {
  AfterContentChecked,
  AfterContentInit,
  AfterViewChecked,
  AfterViewInit,
  Component,
  ContentChild,
  DoCheck,
  ElementRef,
  Input,
  OnChanges,
  OnDestroy,
  OnInit,
  SimpleChanges,
  ViewChild,
  ViewEncapsulation
} from '@angular/core';

@Component({
  selector: 'app-server-element',
  templateUrl: './server-element.component.html',
  styleUrls: ['./server-element.component.css'],
  encapsulation: ViewEncapsulation.Emulated // None, ShadowDom
})
export class ServerElementComponent implements OnInit,
  OnChanges, DoCheck, AfterContentInit, AfterContentChecked, AfterViewInit, AfterViewChecked, OnDestroy {
  @Input() serverName: string;
  @ViewChild('heading', {static: false}) header: ElementRef;
  @ContentChild('paragraphContent', {static: false}) paragraphContent: ElementRef;

  // constructor called!
  // ngDoCheck called!
  // ngAfterContentChecked called!
  // ngAfterViewChecked called!
  // ngOnChanges called!
  // {serverName: SimpleChange}
  // ngOnInit called!
  // ngDoCheck called!
  // ngAfterContentInit called!
  // ngAfterContentChecked called!
  // ngAfterViewInit called!
  // ngAfterViewChecked called!
  // ngOnDestroy called!

  constructor() {
    console.log('constructor called!');
  }

  ngDoCheck(): void {
    console.log('ngDoCheck called!');
  }

  ngAfterContentChecked(): void {
    console.log('ngAfterContentChecked called!');
  }

  ngAfterViewChecked(): void {
    console.log('ngAfterViewChecked called!');
  }

  ngOnChanges(changes: SimpleChanges): void {
    console.log('ngOnChanges called!');
    console.log(changes);
  }

  ngOnInit() {
    console.log('ngOnInit called!');
    console.log('Text Content: ' + this.header);
    console.log('Paragraph Content: ' + this.paragraphContent);
  }

  ngAfterContentInit(): void {
    console.log('ngAfterContentInit called!');
    console.log('Paragraph Content: ' + this.paragraphContent.nativeElement.textContent);
  }

  ngAfterViewInit(): void {
    console.log('ngAfterViewInit called!');
    console.log('Text Content: ' + this.header.nativeElement.textContent);
  }

  ngOnDestroy(): void {
    console.log('ngOnDestroy called!');
  }
}
