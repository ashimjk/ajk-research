import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {RecipesComponent} from './recipes.component';
import {AuthGuardService} from '../auth/auth-guard.service';
import {RecipeStartComponent} from './recipe-start/recipe-start.component';
import {RecipeEditComponent} from './recipe-edit/recipe-edit.component';
import {RecipeDetailComponent} from './recipe-detail/recipe-detail.component';

const recipeRoutes: Routes = [
  {
    path: '', pathMatch: 'full', component: RecipesComponent, canActivate: [AuthGuardService], children: [
      {path: '', component: RecipeStartComponent},
      {path: 'new', component: RecipeEditComponent},
      {path: ':id', component: RecipeDetailComponent},
      {path: ':id/edit', component: RecipeEditComponent}
    ]
  }
];

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forChild(recipeRoutes)
  ],
  exports: [
    RouterModule
  ]
})
export class RecipesRoutingModule {
}
