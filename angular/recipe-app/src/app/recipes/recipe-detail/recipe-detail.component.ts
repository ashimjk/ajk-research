import {Component, Input, OnInit} from '@angular/core';
import {Recipe} from '../recipe.model';
import {RecipeService} from '../recipe.service';
import {ActivatedRoute, Params, Router} from '@angular/router';
import {Store} from '@ngrx/store';
import {AddIngredients} from '../../shopping-list/store/shopping-list-actions.type';
import {AppState} from '../../store/app.state';

@Component({
  selector: 'app-recipe-detail',
  templateUrl: './recipe-detail.component.html',
  styleUrls: ['./recipe-detail.component.css']
})
export class RecipeDetailComponent implements OnInit {

  private id: number;
  @Input() recipe: Recipe;

  constructor(private recipeService: RecipeService,
              private activatedRoute: ActivatedRoute,
              private router: Router,
              private store: Store<AppState>) {
  }

  ngOnInit(): void {
    this.id = +this.activatedRoute.snapshot.params.id;
    this.recipe = this.recipeService.getRecipe(this.id);
    this.activatedRoute.params.subscribe(
      (params: Params) => this.recipe = this.recipeService.getRecipe(+params.id)
    );
  }

  onAddToShoppingList() {
    this.store.dispatch(new AddIngredients(this.recipe.ingredients));
  }

  onDelete() {
    this.recipeService.delete(this.id);
    this.router.navigate(['/recipes']);
  }
}
