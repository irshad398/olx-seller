import {RouterModule, Routes} from "@angular/router";
import {LoginComponent} from "./login/login.component";
import {NgModule} from "@angular/core";
import {RegisterComponent} from "./registration/register.component";
import {HomeComponent} from "./home/home.component";
import {ProductDetailComponent} from "./products/product-detail.component";
import {AuthGuard} from "./authguard";
import {ProductSearchComponent} from "./products/product-search.component";
import {MyProductsComponent} from "./products/my-products.component";
import {MyProductDetailComponent} from "./products/myproduct-detail.component";

const appRoutes: Routes = [
  {
    path: 'login',
    component: LoginComponent
  }, {
    path: 'register',
    component: RegisterComponent

  },
  {
    path: 'home',
    component: HomeComponent, canActivate: [AuthGuard]
    /*,canActivate: [AuthGuard]*/
  },
  {
    path: 'products/detail/:id',
    component: ProductDetailComponent, canActivate: [AuthGuard]
  },
  {
    path: 'home/search',
    component: ProductSearchComponent, canActivate: [AuthGuard]
  },
  {
    path: 'home/my-products',
    component: MyProductsComponent, canActivate: [AuthGuard]
  },
  {
    path: 'my-products/detail/:id',
    component: MyProductDetailComponent, canActivate: [AuthGuard]
  },
  {
    path: '',
    component: LoginComponent

  },

];


@NgModule({
  imports: [
    RouterModule.forRoot(
      appRoutes
    )
  ],
  exports: [
    RouterModule
  ],
  providers: []
})
export class AppRoutingModule {
}
