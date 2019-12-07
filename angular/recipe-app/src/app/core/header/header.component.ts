import {Component} from '@angular/core';
import {DataStorageService} from '../../shared/data-storage.service';
import {AuthService} from '../../auth/auth.service';
import {HttpEvent, HttpEventType} from '@angular/common/http';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html'
})
export class HeaderComponent {

  constructor(private dataStorageService: DataStorageService,
              private authService: AuthService) {
  }

  onFetchRecipes() {
    this.dataStorageService.getRecipes();
  }

  onStoreRecipes() {
    this.dataStorageService.storeRecipes()
      .subscribe((response: HttpEvent<Object>) => {
        console.log('onStoreRecipes', HttpEventType[response.type], response);
      });
  }

  onLogOut() {
    this.authService.logout();
  }

}
