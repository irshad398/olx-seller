import {BrowserModule} from "@angular/platform-browser";
import {FormsModule} from "@angular/forms";
import {HttpModule} from "@angular/http";
import {NgModule} from "@angular/core";

import {RegisterComponent} from "./register.component";
import {RegisterService} from "./register.service";

@NgModule({
  declarations: [

    RegisterComponent
  ],
  imports: [
    BrowserModule,
    HttpModule,
    FormsModule
  ],
  providers: [RegisterService],

})
export class RegisterModule {
}
