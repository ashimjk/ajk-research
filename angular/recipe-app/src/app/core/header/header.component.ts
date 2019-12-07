import {Component, OnInit} from '@angular/core';
import {DataStorageService} from '../../shared/data-storage.service';
import {HttpEvent, HttpEventType} from '@angular/common/http';
import {AppState} from '../../store/app.state';
import {Store} from '@ngrx/store';
import {Observable} from 'rxjs';
import {AuthState} from '../../auth/store/auth.state';
import {LogOut} from '../../auth/store/auth.actions';
import {Router} from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html'
})
export class HeaderComponent implements OnInit {

  authState$: Observable<AuthState>;

  constructor(private dataStorageService: DataStorageService,
              private store: Store<AppState>,
              private router: Router) {
  }

  ngOnInit(): void {
    this.authState$ = this.store.select('auth');
  }

  onFetchRecipes() {
    this.dataStorageService.getRecipes();
  }

  onStoreRecipes() {
    this.dataStorageService.storeRecipes()
      .subscribe((response: HttpEvent<any>) => {
        console.log('onStoreRecipes', HttpEventType[response.type], response);
      });
  }

  onLogOut() {
    this.store.dispatch(new LogOut());
  }

}
