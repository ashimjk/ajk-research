import {Component} from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  selectedFeature = 'recipe';

  onSelectedFeature(selectedFeature: string): void {
    this.selectedFeature = selectedFeature;
  }
}
