import {BrowserModule} from "@angular/platform-browser";
import {ProductSearchComponent} from "./product-search.component";
import {ProductService} from "./product.service";
import {ProductSearchService} from "./product-search.service";
import {NgModule} from "@angular/core";
import {ProductListComponent} from "./product-list.component";
import {ProductDetailComponent} from "./product-detail.component";

@NgModule({
  declarations: [ProductSearchComponent,ProductListComponent,ProductDetailComponent
  ],
  imports: [
    BrowserModule

  ],
  providers: [ProductService,ProductSearchService],
  exports:[ProductSearchComponent,ProductListComponent]

})
export class ProductsModule{}
