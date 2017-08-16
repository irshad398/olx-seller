import {BrowserModule} from "@angular/platform-browser";
import {ProductSearchComponent} from "./products-search/product-search.component";
import {ProductService} from "./product.service";
import {ProductSearchService} from "./products-search/product-search.service";
import {NgModule} from "@angular/core";
import {ProductDetailComponent} from "./product-detail/product-detail.component";
import {FormsModule} from "@angular/forms";
import {RouterModule} from "@angular/router";
import {MyProductsComponent} from "./my-products/my-products.component";
import {MyProductDetailComponent} from "./my-products/myproduct-detail.component";
import {AppRoutingModule} from "../app-routing.module";
import {NgxPaginationModule} from "ngx-pagination";
import {Ng2OrderModule} from "ng2-order-pipe";

@NgModule({
  declarations: [
    ProductSearchComponent,
    ProductDetailComponent,
    MyProductsComponent, MyProductDetailComponent
  ],
  imports: [
    BrowserModule, FormsModule, RouterModule,
    AppRoutingModule, NgxPaginationModule,
    Ng2OrderModule

  ],
  providers: [ProductService, ProductSearchService],
  exports: [ProductSearchComponent]

})
export class ProductsModule {
}
