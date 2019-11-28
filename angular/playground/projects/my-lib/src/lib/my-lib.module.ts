import { NgModule } from '@angular/core';
import { MyLibComponent } from './my-lib.component';
import { LoggerService } from './logger.service';



@NgModule({
  declarations: [MyLibComponent],
  imports: [
  ],
  exports: [MyLibComponent, LoggerService]
})
export class MyLibModule { }
