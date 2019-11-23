import {Component, Input} from '@angular/core';
import {Recipe} from '../../recipe.model';
import {RecipeService} from '../../recipe.service';

@Component({
  selector: 'app-recipe-item',
  templateUrl: './recipe-item.component.html',
  styleUrls: ['./recipe-item.component.css']
})
export class RecipeItemComponent {
  @Input() selectedRecipe: Recipe;

  constructor(private recipeService: RecipeService) {
  }

  onRecipeSelected(): void {
    this.recipeService.getRecipeSelected().emit(this.selectedRecipe);
  }

}
