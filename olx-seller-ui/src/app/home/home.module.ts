import {BrowserModule} from "@angular/platform-browser";
import {NgModule} from "@angular/core";
import {HomeComponent} from "./home.component";
import {ProductsModule} from "../products/products.module";
import {AppRoutingModule} from "../app-routing.module";

@NgModule({
  declarations: [HomeComponent
  ],
  imports: [
    BrowserModule,ProductsModule,AppRoutingModule

  ],
  providers: [],

})
export class HomeModule{}
