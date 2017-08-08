import {BrowserModule} from "@angular/platform-browser";
import {NgModule} from "@angular/core";
import {HomeComponent} from "./home.component";
import {ProductsModule} from "../products/products.module";

@NgModule({
  declarations: [HomeComponent
  ],
  imports: [
    BrowserModule,ProductsModule

  ],
  providers: [],

})
export class HomeModule{}
