import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {ShoppingListComponent} from './shopping-list.component';
import {ShoppingEditComponent} from './shopping-edit/shopping-edit.component';

const shoppingListRoutes: Routes = [
  {
    path: '', component: ShoppingListComponent, children: [
      {path: ':id/edit', component: ShoppingEditComponent}
    ]
  }
];

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forChild(shoppingListRoutes)
  ],
  exports: [
    RouterModule
  ]
})
export class ShoppingListRoutingModule {
}
