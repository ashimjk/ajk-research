import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ShoppingListRoutingModule} from './shopping-list-routing.module';
import {ShoppingListComponent} from './shopping-list.component';
import {ShoppingEditComponent} from './shopping-edit/shopping-edit.component';
import {FormsModule} from '@angular/forms';

@NgModule({
  declarations: [
    ShoppingListComponent,
    ShoppingEditComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    ShoppingListRoutingModule
  ]
})
export class ShoppingListModule {
}
