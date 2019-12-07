import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Params, Router} from '@angular/router';
import {FormArray, FormControl, FormGroup, Validators} from '@angular/forms';
import {Store} from '@ngrx/store';
import {RecipeFeatureState, RecipeState} from '../store/recipe.state';
import {AddRecipe, UpdateRecipe} from '../store/recipe.actions';
import {take} from 'rxjs/operators';

@Component({
  selector: 'app-recipe-edit',
  templateUrl: './recipe-edit.component.html',
  styleUrls: ['./recipe-edit.component.css']
})
export class RecipeEditComponent implements OnInit {
  id: number;
  editMode = false;
  recipeForm: FormGroup;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private store: Store<RecipeFeatureState>) {
  }

  ngOnInit() {
    this.route.params.subscribe(
      (params: Params) => {
        this.id = +params.id;
        this.editMode = params.id != null;
        this.initForm();
      }
    );
  }

  get ingredientsGroup(): FormArray {
    return this.recipeForm.get('ingredients') as FormArray;
  }

  onAddIngredient() {
    this.ingredientsGroup.push(
      new FormGroup({
        name: new FormControl(null, Validators.required),
        amount: new FormControl(null, [Validators.required, Validators.pattern('^[1-9]+[0-9]*$')])
      })
    );
  }

  onRemoveIngredient(index: number) {
    this.ingredientsGroup.removeAt(index);
  }

  onSubmit() {
    // const name = this.recipeForm.get('name').value;
    // const imagePath = this.recipeForm.get('imagePath').value;
    // const description = this.recipeForm.get('description').value;
    // const ingredients = (this.recipeForm.get('ingredients') as FormArray).value;
    // const recipe = new Recipe(name, description, imagePath, ingredients);

    if (this.editMode) {
      this.store.dispatch(new UpdateRecipe({index: this.id, updatedRecipe: this.recipeForm.value}));
    } else {
      this.store.dispatch(new AddRecipe(this.recipeForm.value));
    }

    this.router.navigate(['/recipes']);
  }

  initForm() {
    let name = '';
    let imagePath = '';
    let description = '';
    const ingredientsFormArray = new FormArray([]);

    if (this.editMode) {
      // const recipe = this.recipeService.getRecipe(this.id);
      this.store.select('recipes')
        .pipe(take(1))
        .subscribe((recipeState: RecipeState) => {
          const recipe = recipeState.recipes[this.id];

          name = recipe.name;
          imagePath = recipe.imagePath;
          description = recipe.description;

          for (const ingredient of recipe.ingredients) {
            const ingredientsGroup = new FormGroup({
              name: new FormControl(ingredient.name, Validators.required),
              amount: new FormControl(ingredient.amount, [Validators.required, Validators.pattern('^[1-9]+[0-9]*$')])
            });

            ingredientsFormArray.push(ingredientsGroup);
          }
        });
    }

    this.recipeForm = new FormGroup({
      name: new FormControl(name, Validators.required),
      imagePath: new FormControl(imagePath, Validators.required),
      description: new FormControl(description, Validators.required),
      ingredients: ingredientsFormArray
    });
  }
}
