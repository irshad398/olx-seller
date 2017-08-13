import {BrowserModule} from "@angular/platform-browser";
import {ProductSearchComponent} from "./product-search.component";
import {ProductService} from "./product.service";
import {ProductSearchService} from "./product-search.service";
import {NgModule} from "@angular/core";
import {ProductListComponent} from "./product-list.component";
import {ProductDetailComponent} from "./product-detail.component";
import {FormsModule} from "@angular/forms";
import {RouterModule} from "@angular/router";
import {HomeModule} from "../home/home.module";
import {MyProductsComponent} from "./my-products.component";
import {MyProductDetailComponent} from "./myproduct-detail.component";
import {AppRoutingModule} from "../app-routing.module";
import {NgxPaginationModule} from "ngx-pagination";
import {Ng2OrderModule} from "ng2-order-pipe";

@NgModule({
  declarations: [
    ProductSearchComponent,
    ProductListComponent,
    ProductDetailComponent,
    MyProductsComponent, MyProductDetailComponent
  ],
  imports: [
    BrowserModule, FormsModule, RouterModule,
    AppRoutingModule, NgxPaginationModule,
    Ng2OrderModule

  ],
  providers: [ProductService, ProductSearchService],
  exports: [ProductSearchComponent, ProductListComponent]

})
export class ProductsModule {
}
