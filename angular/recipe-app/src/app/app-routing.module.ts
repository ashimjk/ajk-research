import {NgModule} from '@angular/core';
import {Router, RouterModule, Routes} from '@angular/router';
import {HomeComponent} from './core/home/home.component';
import {SelectivePreloadingStrategyService} from './selective-preloading-strategy.service';
import {AuthGuardService} from './auth/auth-guard.service';

const appRoutes: Routes = [
  {path: '', pathMatch: 'full', component: HomeComponent},
  {
    path: 'recipes',
    loadChildren: () => import('./recipes/recipes.module').then(mod => mod.RecipesModule),
    canLoad: [AuthGuardService]
  },
  // {path: 'recipes', loadChildren: './recipes/recipes.module#RecipesModule'},
  {
    path: 'shopping-list',
    loadChildren: () => import('./shopping-list/shopping-list.module').then(mod => mod.ShoppingListModule),
    data: {
      preload: true
    }
  }
];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes, {
    preloadingStrategy: SelectivePreloadingStrategyService
  })],
  exports: [RouterModule]
})
export class AppRoutingModule {

  // Diagnostic only: inspect router configuration
  constructor(router: Router) {
    // Use a custom replacer to display function names in the route configs
    const replacer = (key, value) => (typeof value === 'function') ? value.name : value;

    // console.log('Routes: ', JSON.stringify(router.config, replacer, 2));
  }
}

