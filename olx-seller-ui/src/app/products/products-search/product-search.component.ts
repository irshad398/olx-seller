import {ProductSearchService} from "./product-search.service";
import {Component, OnInit} from "@angular/core";
import {Product} from "../product";
import {Router} from "@angular/router";

import 'rxjs/add/observable/of';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/debounceTime';
import 'rxjs/add/operator/distinctUntilChanged';
import 'rxjs/add/operator/switchMap';
import {LoginService} from "../../login/login.service";
import {ProductService} from "../product.service";
import {Category} from "../category";

@Component({
  selector: 'product-search',
  templateUrl: './product-search.component.html',
  styleUrls: ['./product-search.component.css'],
})
export class ProductSearchComponent implements OnInit {
  private p: number = 1;
  order: string = "postedOn";
  mode : string = "reverse"
  searchInput = {
    title: '',
    catId: ''
  };
  returnedProducts: Product[];
  categories:Category[];
  selectedProduct: Product;
  msg: boolean;
  username: string;
    constructor(private _productSearchService: ProductSearchService,
              private router: Router, private loginService: LoginService,private _productService:ProductService) {
  }

  ngOnInit(): void {
    this.username=window.localStorage.getItem('user');
    this._productService.getProducts()
      .subscribe(data=>{
        // console.log(data);
        this.returnedProducts=data
      });
    this._productService.getCategories()
      .subscribe(data=>{
        console.log(data);
        this.categories=data;
      });
  }
  getProductsByCat(catId:number){
    console.log("Cat Id:",catId);
    this._productService.getProductsByCat(catId)
      .subscribe(data=>{
        // console.log(data);
        this.returnedProducts=data});
  }
  getProductsOrderBy(orderById:number){
    console.log("orderById: ",orderById);
    this._productService.getProductsOrderBy(orderById)
      .subscribe(data=>{
        // console.log(data);
        this.returnedProducts=data});
  }
  searchProduct(searchData) {
    console.log(searchData)
    this._productSearchService.searchProduct(searchData)
      .subscribe(data => {
        this.returnedProducts = data;
      }, () => {
        console.log("error in searching data")
      });

  }

  selectProduct(product: Product) {
    this.selectedProduct = product;
  }

  gotoDetail(selectedProductId) {

    console.log(selectedProductId);
    this.router.navigate(['products/detail/' + selectedProductId, {id: selectedProductId}]);
  }


  logout() {

    this.loginService.logout().subscribe(data => {
      this.msg = data
      localStorage.clear();
    }, err => {
      console.log("Error", err)
    });
    if (this.msg == true) {
      console.log("Logged out!");
      window.localStorage.clear();
    }
    this.router.navigate(['/login'])
  }

}
