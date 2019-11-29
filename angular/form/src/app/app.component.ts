import {Component} from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  selectedFeature = 'dynamic-form';

  onSelectFeature(feature: string): void {
    this.selectedFeature = feature;
  }

}
