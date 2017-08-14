import {RouterModule, Routes} from "@angular/router";
import {LoginComponent} from "./login/login.component";
import {NgModule} from "@angular/core";
import {RegisterComponent} from "./registration/register.component";
import {HomeComponent} from "./home/home.component";
import {ProductDetailComponent} from "./products/product-detail.component";
import {AuthGuard} from "./authguard";
import {ProductListComponent} from "./products/product-list.component";
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
    path: 'home/products',
    component: ProductListComponent, canActivate: [AuthGuard]
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
    component: HomeComponent

  },

  /*,
  {
    path: 'admin',
    loadChildren: 'app/admin/admin.module#AdminModule',
    canLoad: [AuthGuard]
  },
  {
    path: 'crisis-center',
    loadChildren: 'app/crisis-center/crisis-center.module#CrisisCenterModule',
    data: { preload: true }
  },
  { path: '',   redirectTo: '/heroes', pathMatch: 'full' },
  { path: '**', component: PageNotFoundComponent }*/
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
