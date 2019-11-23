import {Directive, ElementRef, HostBinding, HostListener, Input, OnInit, Renderer2} from '@angular/core';

@Directive({
  selector: '[appBetterHighlight]'
})
export class BetterHighlightDirective implements OnInit {

  @Input() defaultColor = 'transparent';
  @Input('appBetterHighlight') highlightColor = 'red';

  @HostBinding('style.backgroundColor') backgroundColor: string;
  @HostBinding('style.color') color = 'black';

  constructor(private elementRef: ElementRef, private renderer: Renderer2) {
  }

  ngOnInit(): void {
    this.backgroundColor = this.defaultColor;
  }

  @HostListener('mouseenter') mouseover(eventData: Event) {
    // this.renderer.setStyle(this.elementRef.nativeElement, 'backgroundColor', 'red');
    // this.renderer.setStyle(this.elementRef.nativeElement, 'color', 'white');

    this.backgroundColor = this.highlightColor;
    this.color = 'white';
  }

  @HostListener('mouseleave') mouseleave(eventData: Event) {
    // this.renderer.setStyle(this.elementRef.nativeElement, 'backgroundColor', 'transparent');
    // this.renderer.setStyle(this.elementRef.nativeElement, 'color', 'black');

    this.backgroundColor = this.defaultColor;
    this.color = 'black';
  }
}
