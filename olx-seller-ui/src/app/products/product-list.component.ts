
import {Component, OnInit} from "@angular/core";
import {Product} from "./product";
import {ProductService} from "./product.service";
import {Router} from "@angular/router";
import {LoginService} from "../login/login.service";

@Component({
  selector:'product-list',
  templateUrl :'./product-list.component.html',
  styleUrls :['./product-list.component.css'],
})
export class ProductListComponent implements OnInit{
  private p: number = 1;
  order:string="title";
  products:Product[];
  selectedProduct:Product;
  username:string;
  msg:boolean;
  constructor(private _productService: ProductService,private router: Router,private loginService:LoginService){}
  ngOnInit(){
    this._productService.getProducts()
      .subscribe(data=>{
        // console.log(data);
        this.products=data});
    this.loginService.username.subscribe(data=>this.username=data);
  }
  selectProduct(product:Product){
    this.selectedProduct=product;
  }
  gotoDetail(selectedProductId) {

    console.log(selectedProductId);
    this.router.navigate(['products/detail/'+ selectedProductId,{id:selectedProductId}]);
  }
  logout() {

    this.loginService.logout().subscribe(data => {
      this.msg = data
    }, err => {
      console.log("Error", err)
    });
    if (this.msg == true) {
      console.log("Logged out!")
    }
    this.router.navigate(['/login'])
  }

}
