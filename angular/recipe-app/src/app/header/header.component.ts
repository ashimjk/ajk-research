import {Component} from '@angular/core';
import {DataStorageService} from '../shared/data-storage.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html'
})
export class HeaderComponent {

  constructor(private dataStorageService: DataStorageService) {
  }

  onFetchRecipes() {
    this.dataStorageService.getRecipes();
  }

  onStoreRecipes() {
    this.dataStorageService.storeRecipes()
      .subscribe(response => console.log(response));
  }

}
