import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Observable} from 'rxjs';
import {Store} from '@ngrx/store';
import {RecipeFeatureState, RecipeState} from '../store/recipe.state';

@Component({
  selector: 'app-recipe-list',
  templateUrl: './recipe-list.component.html',
  styleUrls: ['./recipe-list.component.css']
})
export class RecipeListComponent implements OnInit {
  recipesState$: Observable<RecipeState>;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private store: Store<RecipeFeatureState>) {
  }

  ngOnInit() {
    this.recipesState$ = this.store.select('recipes');
  }

  onNewRecipe() {
    this.router.navigate(['new'], {relativeTo: this.route});
  }
}
