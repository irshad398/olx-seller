import {BrowserModule} from "@angular/platform-browser";
import {NgModule} from "@angular/core";
import {HomeComponent} from "./home.component";
import {ProductsModule} from "../products/products.module";
import {AppRoutingModule} from "../app-routing.module";
import {NavigationComponent} from "./navigation.component";



@NgModule({
  declarations: [HomeComponent,NavigationComponent
  ],
  imports: [
    BrowserModule,ProductsModule,AppRoutingModule

  ],
  providers: [],
  exports:[NavigationComponent]
})
export class HomeModule{}
