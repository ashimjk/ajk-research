import {Component, EventEmitter, OnInit, Output} from '@angular/core';

import {Recipe} from '../recipe.model';

@Component({
  selector: 'app-recipe-list',
  templateUrl: './recipe-list.component.html',
  styleUrls: ['./recipe-list.component.css']
})
export class RecipeListComponent implements OnInit {
  recipes: Recipe[] = [
    new Recipe('Momo', 'Veg Momo', 'https://upload.wikimedia.org/wikipedia/commons/1/15/Recipe_logo.jpeg'),
    new Recipe('Chowmein', 'Veg Chowmein', 'https://upload.wikimedia.org/wikipedia/commons/1/15/Recipe_logo.jpeg')
  ];

  @Output() itemSelected = new EventEmitter<Recipe>();

  constructor() {
  }

  ngOnInit() {
  }

  onItemSelected(recipe: Recipe) {
    this.itemSelected.emit(recipe);
  }

}
