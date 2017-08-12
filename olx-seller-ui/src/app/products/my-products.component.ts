import {Component, OnInit} from "@angular/core";
import {Product} from "./product";
import {ProductService} from "./product.service";
import {Router} from "@angular/router";

@Component({
  selector:'my-products',
  templateUrl :'./my-products.component.html',
  styleUrls :['./my-products.component.css'],
})
export class MyProductsComponent implements OnInit{
  myProducts:Product[];
  selectedProduct:Product;

  constructor(private _productService: ProductService,private router: Router){}
  ngOnInit(){
    this._productService.getMyProducts()
      .subscribe(data=>{
        console.log(data);
        this.myProducts=data});
  }
  selectProduct(product:Product){
    this.selectedProduct=product;
  }
  gotoDetail(selectedProductId) {

    console.log(selectedProductId);
    this.router.navigate(['products/detail/'+ selectedProductId,{id:selectedProductId}]);
  }
  gotoMyProductDetail(selectedProductId) {
    console.log(selectedProductId);
    this.router.navigate(['my-products/detail/'+ selectedProductId,{id:selectedProductId}]);
  }
}
