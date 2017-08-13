
import {Component, OnInit} from "@angular/core";
import {Product} from "./product";
import {ProductService} from "./product.service";
import {Router} from "@angular/router";

@Component({
  selector:'product-list',
  templateUrl :'./product-list.component.html',
  styleUrls :['./product-list.component.css'],
})
export class ProductListComponent implements OnInit{
  private p: number = 1;
  products:Product[];
  selectedProduct:Product;

  constructor(private _productService: ProductService,private router: Router){}
  ngOnInit(){
    this._productService.getProducts()
      .subscribe(data=>{
        // console.log(data);
        this.products=data});
  }
  selectProduct(product:Product){
    this.selectedProduct=product;
  }
  gotoDetail(selectedProductId) {

    console.log(selectedProductId);
    this.router.navigate(['products/detail/'+ selectedProductId,{id:selectedProductId}]);
  }

}
