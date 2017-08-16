import {Component, OnInit} from "@angular/core";
import {Product} from "../products/product";
import {ProductService} from "../products/product.service";
import {Router} from "@angular/router";

@Component({
  // selector:'seller-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],

})
export class HomeComponent implements OnInit {
  recentProducts: Product[];

  constructor(private _productService: ProductService,private router:Router) {
  }

  ngOnInit() {
    this._productService.getRecentProducts()
      .subscribe(data => {
        console.log("recent products:",data);
        this.recentProducts = data
      });
  }
  gotoDetail(selectedProductId) {

    console.log(selectedProductId);
    this.router.navigate(['products/detail/' + selectedProductId, {id: selectedProductId}]);
  }
}
