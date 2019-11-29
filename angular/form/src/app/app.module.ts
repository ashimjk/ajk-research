import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {ReactiveDrivenComponent} from './reactive-driven/reactive-driven.component';
import {TemplateDrivenComponent} from './template-driven/template-driven.component';
import {QuestionShellComponent} from './questions/question-shell/question-shell.component';
import {QuestionAddComponent} from './questions/question-add/question-add.component';
import {QuestionFormComponent} from './questions/question-add/question-form/question-form.component';

@NgModule({
  declarations: [
    AppComponent,
    ReactiveDrivenComponent,
    TemplateDrivenComponent,
    QuestionShellComponent,
    QuestionAddComponent,
    QuestionFormComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
