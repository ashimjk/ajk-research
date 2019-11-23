import {Component, EventEmitter, Output} from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html'
})
export class HeaderComponent {
  feature = 'recipe';
  @Output() selectedFeature = new EventEmitter<string>();

  onSelected(feature: string): void {
    this.feature = feature;
    this.selectedFeature.emit(feature);
  }

}
